package com.sciencom.di.consumer;

import com.sciencom.di.service.MessageService;

public class ConsumerImpl implements Consumer {
	private MessageService messageService;
	
	public ConsumerImpl(MessageService messageService) {
		this.messageService = messageService;
	}

	public MessageService getMessageService() {
		return messageService;
	}

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

	@Override
	public void processMessage(String message, String receiver) {
		// TODO Auto-generated method stub
		this.messageService.sendMessage(message, receiver);
	}
	
}
