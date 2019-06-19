package id.go.bps.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import id.go.bps.jpa.exception.TransferFundException;
import id.go.bps.jpa.model.BankAccount;

@Repository
public class BankAccountServiceImpl implements TransferFundService{
	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public int addAmount(Long id, Double amount) throws TransferFundException {
		// TODO Auto-generated method stub
		// 1. find BankAccount exist or not
//		BankAccount bankAccount = this.findBankAccountById(id);
//		if (bankAccount==null) {
//			throw new TransferFundException("Bank Account "+id+" not found!");
//		}
//		// 2. Check balance sufficient or not
//		Double newbalance = bankAccount.getBalance() + amount;
//		if (bankAccount.getBalance() + amount < 0) {
//			throw new TransferFundException("Money in account "+id+" is not enough");
//		}
//		bankAccount.setBalance(newbalance);
		
		return 0;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = TransferFundException.class)
	public void TransferFund(Long fromAccount, Long toAccount, Double amount) throws TransferFundException {
		// TODO Auto-generated method stub
		addAmount(fromAccount, -amount);
		addAmount(toAccount, amount);
	}
}
