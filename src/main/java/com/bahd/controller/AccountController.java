package com.bahd.controller;

import com.bahd.enums.AccountType;
import com.bahd.model.Account;
import com.bahd.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

@Controller
public class AccountController {

    /*
        write a method to return index.html including account list information
        endpoint: index
     */

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/index")
    public String getIndexPage( Model model) {

          model.addAttribute("accountList", accountService.listAllAccount());

        return "account/index";
    }

    @GetMapping("/create-form")
    public String getCreateForm(Model model){
        // we need to send an empty account object
        model.addAttribute("account", Account.builder().build());
        //we need to provide account type enum for filling dropdown
        model.addAttribute("accountTypes", AccountType.values());
        return "account/create-account";
    }

    //creat a method to capture information from UI
    //print them on console
    //trigger createNewAccount method, create the account base on the user input
    //once user created  return back to the index page

    @PostMapping("/create")
    public String createAccount(@ModelAttribute("account") Account account){
        System.out.println(account);
        accountService.createNewAccount(account.getBalance(), new Date(), account.getAccountType(), account.getUserId());
        return "redirect:/index";
    }
}
