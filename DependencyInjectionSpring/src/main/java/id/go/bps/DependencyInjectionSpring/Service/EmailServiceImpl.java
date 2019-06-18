package id.go.bps.DependencyInjectionSpring.Service;

import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements MessageService{

	@Override
	public void sendMessage(String message, String receiver) {
		// TODO Auto-generated method stub
		System.out.println("Email Send to: "+ receiver +" with message: "+ message);
	}
	
}
