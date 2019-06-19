package id.go.bps.jdbc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import id.go.bps.jdbc.exception.TransferFundException;
import id.go.bps.jdbc.model.BankAccount;

@Repository
public class BankAccountServiceImpl implements BankAccountService{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	@Override
	public String addBankAccount(BankAccount bankAccount) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO bank_account(first_name,last_name,balance) "
				+ "VALUE (?,?,?)";
		int result = getJdbcTemplate().update(sql, 
				bankAccount.getFirstName(), 
				bankAccount.getLastName(), 
				bankAccount.getBalance());
		if(result==1) {
			return "Add Record Success";
		}else {
			return "Add Record Failed";
		}
	}

	@Override
	public String editBankAccount(BankAccount bankAccount, Long id) {
		// TODO Auto-generated method stub
		String sql = "UPDATE bank_account "
				+ "SET first_name=?, "
				+ "last_name=?, "
				+ "balance=? WHERE id=?";
		int result = getJdbcTemplate().update(sql, 
				bankAccount.getFirstName(), 
				bankAccount.getLastName(), 
				bankAccount.getBalance(), id);
		if(result==1) {
			return "Update Record Success";
		}else {
			return "Update Record Failed";
		}
	}

	@Override
	public String deleteBankAccount(Long id) {
		// TODO Auto-generated method stub
		String sql = "DELETE bank_account WHERE id=?";
		int result = getJdbcTemplate().update(sql, id);
		if(result==1) {
			return "Delete Record Success";
		}else {
			return "Delete Record Failed";
		}
	}

	@Override
	public BankAccount findBankAccountById(Long id) {
		String sql = "SELECT * FROM bank_account WHERE id=?";
		try {
			BankAccount bankAccount = getJdbcTemplate().queryForObject(sql, 
					new Object[] {id}, new BeanPropertyRowMapper<>(BankAccount.class));
			return bankAccount;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<BankAccount> findAll() {
		String sql = "SELECT * FROM bank_account";
		List<BankAccount> bankAccountList = getJdbcTemplate().query(sql, 
				new BeanPropertyRowMapper<>(BankAccount.class));
		return bankAccountList;
	}

	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public int addAmount(Long id, Double amount) throws TransferFundException {
		// TODO Auto-generated method stub
		// 1. find BankAccount exist or not
		BankAccount bankAccount = this.findBankAccountById(id);
		if (bankAccount==null) {
			throw new TransferFundException("Bank Account "+id+" not found!");
		}
		// 2. Check balance sufficient or not
		Double newbalance = bankAccount.getBalance() + amount;
		if (bankAccount.getBalance() + amount < 0) {
			throw new TransferFundException("Money in account "+id+" is not enough");
		}
		bankAccount.setBalance(newbalance);
		String sql = "UPDATE bank_account "
				+ "SET balance=? WHERE id=?";
		int result = getJdbcTemplate().update(sql,  
				bankAccount.getBalance(), id);
		
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = TransferFundException.class)
	public void TransferFund(Long fromAccount, Long toAccount, Double amount) throws TransferFundException {
		// TODO Auto-generated method stub
		addAmount(fromAccount, -amount);
		addAmount(toAccount, amount);
	}
}
