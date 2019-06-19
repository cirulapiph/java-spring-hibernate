package id.go.bps.jpa.service;

import org.springframework.data.repository.CrudRepository;

import id.go.bps.jpa.model.BankAccount;

public interface BankAccountService extends CrudRepository<BankAccount, Long>{
	
}
