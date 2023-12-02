package com.bahd.service.impl;

import com.bahd.dto.AccountDTO;
import com.bahd.enums.AccountStatus;
import com.bahd.enums.AccountType;
import com.bahd.repository.AccountRepository;
import com.bahd.service.AccountService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class AccountServiceImpl implements AccountService {

    AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDTO createNewAccount(BigDecimal balance, Date createDate, AccountType accountType, Long userId) {
        //we need to create account object
        AccountDTO accountDTO = new AccountDTO();

        //save into database(repository)
        //return the object created
        return accountRepository.save(accountDTO);
    }

    @Override
    public List<AccountDTO> listAllAccount() {
        //we are getting list of account but we need to retrun list of accountDto
        return accountRepository.findAll();
    }

     @Override
    public void deleteAccount(Long id) {
        //find the account belongs the id
       AccountDTO accountDTO = accountRepository.findById(id);
       //set status to deleted
         accountDTO.setAccountStatus(AccountStatus.DELETED);

    }

    @Override
    public void activateAccount(Long id) {
        //find the account belongs the id
        AccountDTO accountDTO = accountRepository.findById(id);
        //set status to active
        accountDTO.setAccountStatus(AccountStatus.ACTIVE);
    }

    @Override
    public AccountDTO retrieveById(Long id) {
        return accountRepository.findById(id);
    }
}
