package com.sciencom.di.injector;

import com.sciencom.di.consumer.Consumer;
import com.sciencom.di.consumer.ConsumerImpl;
import com.sciencom.di.service.EmailServiceImpl;

public class EmailServiceInjector implements MessageServiceInjector{

	@Override
	public Consumer getConsumer() {
		// TODO Auto-generated method stub
		return new ConsumerImpl(new EmailServiceImpl());
	}
	
}
