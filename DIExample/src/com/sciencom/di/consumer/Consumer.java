package com.sciencom.di.consumer;

public interface Consumer {
	public void processMessage(String message, String receiver);
}
