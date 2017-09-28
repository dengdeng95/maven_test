package redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Set;

public class RedisTest {

	/**
	 * 读:  读redis->没有，读mysql->把mysql数据写回redis
	 * 写:  写mysql->成功，写redis
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JedisPool jedisPool = JedisPoolTest.getInstance();
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			Set<String> a = jedis.smembers("denghf");
			for(String s:a){
				System.out.println(s);
			}
			System.out.println(jedis.smembers("denghf"));
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JedisPoolTest.release(jedisPool, jedis);
		}
	}

}
