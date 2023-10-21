package com.bahd.service;

import com.bahd.model.Account;
import com.bahd.model.Transaction;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface TransactionService {

    Transaction makeTransfer(Account sender, Account receiver, BigDecimal amount, Date creationDate, String message);

    List<Transaction> findAllTransaction();

    List<Transaction> last10Transactions();

    List<Transaction> findTransactionById(UUID id);
}
