package com.sciencom.legacy;

public class MyApplicationInjector {
	private EmailService email = null;
	
	
	// Alt+Shift+S generate constructor
	public MyApplicationInjector(EmailService email) {
		this.email = email;
	}
	
	public void processMessage(String message, String receiver) {
		this.email.sendMessage(message, receiver);
	}
}
