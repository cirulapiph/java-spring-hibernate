package com.sciencom.legacy;

public class MyApplicationTest {
	
	private static final EmailService emailService = new EmailService();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyApplicationInjector app = new MyApplicationInjector(emailService);
		app.processMessage("Hi, Afif", "choerulafifanto@gmail.com");
		
		// bentuk seperti ini menandakan MyApplicationTest masih sangat bergantung pada EmailService
		EmailService obj = new EmailService();
		obj.sendMessage("Hi", "choerulafifanto2gmail.com");
		
		// bentuk seperti ini menandakan MyApplicationTest masih sangat bergantung pada TwitterService
		TwitterService obj2 = new TwitterService();
		obj2.sendMessage("Hi", "@cirulapiphh");
	}

}
