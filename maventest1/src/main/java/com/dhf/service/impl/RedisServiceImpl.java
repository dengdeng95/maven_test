package com.dhf.service.impl;

import com.dhf.model.ObSystemAccount;
import com.dhf.model.User;
import com.dhf.redis.RedisLock;
import com.dhf.service.RedisService;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class RedisServiceImpl implements RedisService{
	
	@Resource
	public RedisTemplate<Serializable, Serializable> redisTemplate;
	
	/**
	 * 监听虚拟账户的金额 并更改
	 * redis
	 * @param obSystemAccount
	 */
	public void redis_updatemoney(final ObSystemAccount obSystemAccount){
		redisTemplate.watch("balance");
		redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				// TODO Auto-generated method stub
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				byte[] balance = serializer.serialize("balance");
				byte[] debt = serializer.serialize("debt");
				if(connection.exists(balance) && connection.exists(debt)){
					if(obSystemAccount.getMoney().compareTo(new BigDecimal(redisTemplate.getStringSerializer().deserialize(connection.get(balance))))>0){
						redisTemplate.unwatch();
						System.out.println("取消对balance的监听");
					}else{
						Long a = connection.incrBy(balance, Long.valueOf(obSystemAccount.getMoney().toString()));
						connection.incrBy(debt, Long.valueOf(obSystemAccount.getMoney().toString()));
						System.out.println("a=="+a);
					}
				}
				return null;
			}
		});
	}
	
	/**
	 * 读取余额字段
	 * * redis
	 */
	public ObSystemAccount redis_getMoney(){
		return redisTemplate.execute(new RedisCallback<ObSystemAccount>() {
            @Override
            public ObSystemAccount doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] balance = redisTemplate.getStringSerializer().serialize("balance");
                if (connection.exists(balance)) {
                    byte[] balanceByte = connection.get(balance);
                    String balanceValue = redisTemplate.getStringSerializer().deserialize(balanceByte);
                    ObSystemAccount ob = new ObSystemAccount();
                    ob.setTotalmoney(new BigDecimal(balanceValue));
                    return ob;
                }
                return null;
            }
        });
	}
	
	/**
	 * redis中用set集合添加相应的用户信息 默认账户为0元
	 * @return
	 * @throws IOException 
	 */
	public void redis_sadd(final User u,final ObSystemAccount ob) throws IOException{
		final Map<byte[],byte[]> map = new HashMap<>();
		
		redisTemplate.execute(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				// TODO Auto-generated method stub
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				byte[] id  = serializer.serialize(String.valueOf(u.getId()));  
				byte[] name  = serializer.serialize(u.getName());  
				byte[] age  = serializer.serialize(u.getAge());  
				byte[] card  = serializer.serialize(u.getCard());  
				byte[] totalMoney  = serializer.serialize(String.valueOf(ob.getTotalmoney()));  
				map.put("id".getBytes(), id);
				map.put("name".getBytes(), name);
				map.put("age".getBytes(), age);
				map.put("card".getBytes(), card);
				map.put("totalMoney".getBytes(), totalMoney);
				connection.hMSet(name, map);
				return "";
			}
			
		});
	}
	
	/**
	 * get某个key
	 */
	public String redis_get(Object o){
		redisTemplate.execute(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				
				return null;
			}
		});
		return null;
	}

	/**
	 * redis_hmget
	 * @return
	 */
	public BigDecimal redis_hmget(final ObSystemAccount obSystemAccount){
		final BigDecimal bigDecimal;
		bigDecimal = redisTemplate.execute(new RedisCallback<BigDecimal>() {
			@Override
			public BigDecimal doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				List<byte[]> list = connection.hMGet(obSystemAccount.getInvestpersonname().getBytes(), "totalMoney".getBytes());
				String big = serializer.deserialize(list.get(0));
				return new BigDecimal(big);
			}
		});
		return bigDecimal;
	}
	
	/**
	 * redis_hmset
	 * @return
	 */
	public void redis_hmset(final ObSystemAccount obSystemAccount){
		final Map<byte[],byte[]> map = new HashMap<>();
		
		redisTemplate.execute(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				byte[] totalMoney  = serializer.serialize(String.valueOf(obSystemAccount.getTotalmoney())); 
				map.put("totalMoney".getBytes(), totalMoney);
				connection.hMSet(obSystemAccount.getInvestpersonname().getBytes(), map);
				return "";
			}
		});
	}

	/**
	 * 使用redis分布式锁来控制并发
	 */
	public BigDecimal redis_setNx(ObSystemAccount obSystemAccount) {
		RedisLock lock = new RedisLock(redisTemplate, obSystemAccount.getInvestpersonname(), 10000, 20000);
		BigDecimal totalMoneyNew = null;
		try {
			if (lock.lock()) {
				BigDecimal totalMoney = redis_hmget(obSystemAccount);
				totalMoney = totalMoney.add(obSystemAccount.getMoney());
				obSystemAccount.setTotalmoney(totalMoney);
				redis_hmset(obSystemAccount);
				//取redis中最新的totalMoney的值
				totalMoneyNew = redis_hmget(obSystemAccount);
				System.out.println(totalMoneyNew);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			//为了让分布式锁的算法更稳键些，持有锁的客户端在解锁之前应该再检查一次自己的锁是否已经超时，再去做DEL操作，因为可能客户端因为某个耗时的操作而挂起，
			//操作完的时候锁因为超时已经被别人获得，这时就不必解锁了。
			
			//if(!lock.isovertime()){
			lock.unlock();
			//}
		}
		return totalMoneyNew;
	}
		
}
