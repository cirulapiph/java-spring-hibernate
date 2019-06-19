package id.go.bps.jpa.service;

import id.go.bps.jpa.exception.TransferFundException;

public interface TransferFundService {

	int addAmount(Long id, Double amount) throws TransferFundException;

	void TransferFund(Long fromAccount, Long toAccount, Double amount) throws TransferFundException;
	
}
