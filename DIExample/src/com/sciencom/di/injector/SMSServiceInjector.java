package com.sciencom.di.injector;

import com.sciencom.di.consumer.Consumer;
import com.sciencom.di.consumer.ConsumerImpl;
import com.sciencom.di.service.SMSServiceImpl;

public class SMSServiceInjector implements MessageServiceInjector{

	@Override
	public Consumer getConsumer() {
		// TODO Auto-generated method stub
		return new ConsumerImpl(new SMSServiceImpl());
	}

}
