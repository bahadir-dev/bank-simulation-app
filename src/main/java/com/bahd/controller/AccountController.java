package com.bahd.controller;

import com.bahd.model.Account;
import com.bahd.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

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
}
