package com.dhf.jedisPool;

import java.io.IOException;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolInit {
	private JedisPoolInit(){}
	private static volatile JedisPool jedisPool = null;
	
	public static synchronized JedisPool getInstance() throws IOException{
		if(jedisPool == null){
			JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
			jedisPoolConfig.setMaxActive(1000);
			jedisPoolConfig.setMaxIdle(32);
			jedisPoolConfig.setMaxWait(100*1000);
			
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
