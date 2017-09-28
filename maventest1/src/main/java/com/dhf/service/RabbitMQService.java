package com.dhf.service;


public interface RabbitMQService {
    
	public void sendQueue(String exchange_key, String queue_key, Object object);
}
