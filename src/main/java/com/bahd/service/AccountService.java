package com.bahd.service;

import com.bahd.enums.AccountType;
import com.bahd.model.Account;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface AccountService {

    Account createNewAccount(BigDecimal balance, Date createDate, AccountType accountType, Long userId);

    List<Account> listAllAccount();
    void deleteAccount(UUID id);

    void activateAccount(UUID id);

}
