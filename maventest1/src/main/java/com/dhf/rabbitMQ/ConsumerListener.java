package com.dhf.rabbitMQ;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class ConsumerListener implements MessageListener{

	@Override
	public void onMessage(Message arg0) {
		// TODO Auto-generated method stub
		System.out.println("bbbbb = "+arg0.toString());
	}

}
