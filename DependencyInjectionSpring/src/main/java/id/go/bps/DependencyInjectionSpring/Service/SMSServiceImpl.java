package id.go.bps.DependencyInjectionSpring.Service;

import org.springframework.stereotype.Service;

@Service
public class SMSServiceImpl implements MessageService{

	@Override
	public void sendMessage(String message, String receiver) {
		// TODO Auto-generated method stub
		System.out.println("SMS Send to: "+ receiver +" with message: "+ message);
	}
	
}
