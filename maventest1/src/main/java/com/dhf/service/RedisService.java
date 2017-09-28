package com.dhf.service;

import java.io.IOException;
import java.math.BigDecimal;

import com.dhf.model.ObSystemAccount;
import com.dhf.model.User;

public interface RedisService {
	public void redis_updatemoney(final ObSystemAccount obSystemAccount);

	public ObSystemAccount redis_getMoney();
	
	public void redis_sadd(User u,ObSystemAccount ob) throws IOException;

	BigDecimal redis_setNx(ObSystemAccount obSystemAccount);

	BigDecimal redis_hmget(ObSystemAccount obSystemAccount);
	
	void redis_hmset(final ObSystemAccount obSystemAccount);// Object ... o
	
	String redis_get(Object o);
}
