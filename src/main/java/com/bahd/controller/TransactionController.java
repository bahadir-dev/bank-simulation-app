package com.bahd.controller;

import com.bahd.enums.AccountStatus;
import com.bahd.model.Transaction;
import com.bahd.service.AccountService;
import com.bahd.service.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TransactionController {

    private final AccountService accountService;
    private final TransactionService transactionService;

    public TransactionController(AccountService accountService, TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    @GetMapping("/make-transfer")
    public String getMakeTransfer(Model model){
        //what do we need to provide to make transfer happen : empty transaction object
        model.addAttribute("transaction", Transaction.builder().build());
        //we need to provide the list of all accounts
        model.addAttribute("accounts", accountService.listAllAccount());
        //we need to list of last ten transactions
        model.addAttribute("lastTransactions", transactionService.last10Transactions());


        return "transaction/make-transfer";
    }

    //write a post method that takes transaction object from the UI
    //complete the transfer and return the same page
}
