package id.go.bps.DependencyInjectionSpring.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import id.go.bps.DependencyInjectionSpring.Service.MessageService;

@Component
public class ConsumerDI {
	// 1. Field Injection
	@Autowired
//	@Qualifier("getEmailService")
	@Qualifier("getSMSService")
	private MessageService messageService;
	
	// 2. Constructor Injection
	//@Autowired
//	public ConsumerDI(MessageService messageService) {
//		super();
//		this.messageService = messageService;
//	}
	
	// 3. Setter Injection
	//@Autowired
//	public void setMessageService(MessageService messageService) {
//		this.messageService = messageService;
//	}
	
	public void processMessage(String message, String receiver) {
		this.messageService.sendMessage(message, receiver);
	}
}
