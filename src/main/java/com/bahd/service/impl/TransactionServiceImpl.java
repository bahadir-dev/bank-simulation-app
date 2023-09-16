package com.bahd.service.impl;

import com.bahd.exception.BadRequestException;
import com.bahd.model.Account;
import com.bahd.model.Transaction;
import com.bahd.service.TransactionService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Component
public class TransactionServiceImpl implements TransactionService {
    @Override
    public Transaction makeTransfer(Account sender, Account receiver, BigDecimal amount, Date creationDate, String message) {
        /*
        if sender or receiver is null
        if sender & receiver the same account
        if sender has enough balance to make transfer
        if both accounts are checking, if not, one of them saving, it needs to be same userId
         */

        validateAccount(sender, receiver);
        return null;
    }

    private void validateAccount(Account sender, Account receiver) {
        /*
        if any account is null
        if account ids are the same
        if the account exist in database
         */
        if(sender ==null || receiver== null){
            throw new BadRequestException("Sender or Receiver cannot be null");
        }

    }

    @Override
    public List<Transaction> findAllTransaction() {
        return null;
    }
}
