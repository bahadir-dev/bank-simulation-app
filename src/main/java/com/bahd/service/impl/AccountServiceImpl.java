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
        AccountDTO accountDTO = AccountDTO.builder().id(UUID.randomUUID()).userId(userId)
                .balance(balance).accountType(accountType).creationDate(createDate)
                .accountStatus((AccountStatus.ACTIVE)).build();
        //save into database(repository)
        //return the object created
        return accountRepository.save(accountDTO);
    }

    @Override
    public List<AccountDTO> listAllAccount() {
        return accountRepository.findAll();
    }

     @Override
    public void deleteAccount(UUID id) {
        //find the account belongs the id
       AccountDTO accountDTO = accountRepository.findById(id);
       //set status to deleted
         accountDTO.setAccountStatus(AccountStatus.DELETED);

    }

    @Override
    public void activateAccount(UUID id) {
        //find the account belongs the id
        AccountDTO accountDTO = accountRepository.findById(id);
        //set status to active
        accountDTO.setAccountStatus(AccountStatus.ACTIVE);
    }

    @Override
    public AccountDTO retrieveById(UUID id) {
        return accountRepository.findById(id);
    }
}
