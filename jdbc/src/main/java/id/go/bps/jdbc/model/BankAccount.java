package id.go.bps.jdbc.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BankAccount {
	private Long id;
	private String firstName;
	private String lastName;
	private Double balance;
	
	public BankAccount() {
		
	}	
}
