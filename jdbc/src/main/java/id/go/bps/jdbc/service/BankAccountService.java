package id.go.bps.jdbc.service;

import java.util.List;

import id.go.bps.jdbc.exception.TransferFundException;
import id.go.bps.jdbc.model.BankAccount;

public interface BankAccountService {
	public String addBankAccount(BankAccount bankAccount);
	public String editBankAccount(BankAccount bankAccount, Long id);
	public String deleteBankAccount(Long id);
	public BankAccount findBankAccountById(Long id);
	public List<BankAccount> findAll();
	public int addAmount(Long id, Double amount) throws TransferFundException;
	public void TransferFund(Long fromAccount, Long toAccount, Double amount) throws TransferFundException;
}
