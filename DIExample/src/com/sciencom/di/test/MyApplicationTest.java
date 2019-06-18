package com.sciencom.di.test;

import com.sciencom.di.consumer.Consumer;
import com.sciencom.di.injector.EmailServiceInjector;
import com.sciencom.di.injector.MessageServiceInjector;
import com.sciencom.di.injector.SMSServiceInjector;

public class MyApplicationTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String _message = "Hello Afif";
		String _email = "choerulafifanto@gmail.com";
		String _phone = "+6285640397405";
		
		MessageServiceInjector injector = null;
		Consumer app = null;
		
		// send email
		injector = new EmailServiceInjector();
		app = injector.getConsumer();
		app.processMessage(_message, _email);
		
		// send SMS
		injector = new SMSServiceInjector();
		app = injector.getConsumer();
		app.processMessage(_message, _phone);
	}

}
