package id.go.bps.jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import id.go.bps.jpa.exception.TransferFundException;
import id.go.bps.jpa.model.BankAccount;
import id.go.bps.jpa.service.BankAccountService;
import id.go.bps.jpa.transaction.TransferFund;

@Controller
public class BankAccountController {
	
	@Autowired
	private BankAccountService bankAccountService;
	
	@GetMapping(value= {"/","/index"})
	public String listBankString(Model model) {
		List<BankAccount> listBankAccounts = (List<BankAccount>) bankAccountService.findAll();
		model.addAttribute("listBankAccounts", listBankAccounts);
		return "AccountListPage";
	}
	
	@GetMapping("/transferFund")
	public String transferFund(Model model) {
//		TransferFund transferFund = new TransferFund();
//		model.addAttribute("transferFund", transferFund);
		return "TransferFundPage";
	}
	
	@PostMapping("/transferFund")
	public String transferFund(Model model, TransferFund transferFund) {
//		try {
//			bankAccountService.TransferFund(transferFund.getFromAccount(), 
//					transferFund.getToAccount(), 
//					transferFund.getBalance());
//		} catch (TransferFundException e) {
//			model.addAttribute("errorMessage", "Error : "+e.getMessage());
//			return "TransferFundPage";
//		}
		return "redirect:/";
	}
}
