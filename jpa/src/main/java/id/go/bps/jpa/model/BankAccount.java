package id.go.bps.jpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

@Entity
@Table(name="bank_account")
public class BankAccount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="bank_id", nullable = false)
	private Long id;
	
	@Column(name="first_name", nullable = false, length=100)
	private String firstName;
	
	@Column(name="last_name", nullable = false, length=100)
	private String lastName;
	
	@Column(name="balance", nullable = false)
	private Double balance;
	
	public BankAccount() {
		
	}	
}
