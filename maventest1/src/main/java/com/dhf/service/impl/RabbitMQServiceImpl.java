package com.dhf.service.impl;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhf.service.RabbitMQService;

@Service
public class RabbitMQServiceImpl implements RabbitMQService{
	@Autowired
    private AmqpTemplate amqpTemplate;
    
	public void sendQueue(String exchange_key, String queue_key, Object object) {
        // convertAndSend 将Java对象转换为消息发送至匹配key的交换机中Exchange
		amqpTemplate.convertAndSend("queue_one_key",object);
    }
}
