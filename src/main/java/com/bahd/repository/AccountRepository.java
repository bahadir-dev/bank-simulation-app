package com.bahd.repository;

import com.bahd.exception.RecordNotFoundException;
import com.bahd.dto.AccountDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountRepository {
    public static List<AccountDTO> accountDTOList = new ArrayList<>();

    public AccountDTO save(AccountDTO accountDTO){
        accountDTOList.add(accountDTO);
        return accountDTO;
    }

    public List<AccountDTO> findAll() {
        return accountDTOList;
    }

    public AccountDTO findById(Long id) {
        //Task: write a method that finds the account inside the list
        //if not throw record not found exception
        return accountDTOList.stream()
                .filter(account-> account.getId().equals(id))
                .findAny().orElseThrow(()-> new RecordNotFoundException("No Record of account is found"));
    }
}
