package com.bahd.repository;

import com.bahd.exception.RecordNotFoundException;
import com.bahd.model.Account;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class AccountRepository {
    public static List<Account> accountList = new ArrayList<>();

    public Account save(Account account){
        accountList.add(account);
        return account;
    }

    public List<Account> findAll() {
        return accountList;
    }

    public Account findById(UUID id) {
        //Task: write a method that finds the account inside the list
        //if not throw record not found exception
        return accountList.stream()
                .filter(account-> account.getId().equals(id))
                .findAny().orElseThrow(()-> new RecordNotFoundException("No Record of account is found"));
    }
}
