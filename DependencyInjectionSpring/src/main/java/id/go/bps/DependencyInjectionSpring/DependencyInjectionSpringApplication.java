package id.go.bps.DependencyInjectionSpring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import id.go.bps.DependencyInjectionSpring.Config.DIConfig;
import id.go.bps.DependencyInjectionSpring.Consumer.ConsumerDI;

@SpringBootApplication
public class DependencyInjectionSpringApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(DependencyInjectionSpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		String _message = "Hi Afif";
		String _email = "choerulafifanto@gmail.com";
		
		AnnotationConfigApplicationContext contex = new AnnotationConfigApplicationContext(DIConfig.class);
		
		ConsumerDI app = contex.getBean(ConsumerDI.class);
		
		app.processMessage(_message, _email);
		
		contex.close();
	}

}
