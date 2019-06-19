package id.go.bps.jdbc.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransferFund {
	private Long fromAccount;
	private Long toAccount;
	private Double balance;
	
	public TransferFund() {
		// TODO Auto-generated constructor stub
	}
	
	
}
