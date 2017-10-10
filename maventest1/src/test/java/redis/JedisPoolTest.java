package redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolTest {
	private JedisPoolTest(){}
	private static volatile JedisPool jedisPool = null;
	
	public static synchronized JedisPool getInstance(){
		if(jedisPool == null){
			JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
			jedisPoolConfig.setMaxTotal(1000);
			jedisPoolConfig.setMaxIdle(32);
			jedisPoolConfig.setMaxWaitMillis(100*1000);
			
			jedisPool = new JedisPool("127.0.0.1",6379);
		}
		return jedisPool;
	}
	
	public static void release(JedisPool jedisPool,Jedis jedis){
		if(jedis!=null){
			jedisPool.returnResourceObject(jedis);
		}
	}
}
