package id.go.bps.DependencyInjectionSpring.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import id.go.bps.DependencyInjectionSpring.Service.EmailServiceImpl;
import id.go.bps.DependencyInjectionSpring.Service.MessageService;
import id.go.bps.DependencyInjectionSpring.Service.SMSServiceImpl;

@Configuration
@ComponentScan(value= {"id.go.bps.DependencyInjectionSpring.Consumer"})
public class DIConfig {
	
	@Bean
	public MessageService getEmailService() {
		return new EmailServiceImpl();
	}
	
	@Bean
	public MessageService getSMSService() {
		return new SMSServiceImpl();
	}
}
