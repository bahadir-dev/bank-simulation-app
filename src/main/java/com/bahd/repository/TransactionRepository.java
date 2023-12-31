package com.bahd.repository;

import com.bahd.model.Transaction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class TransactionRepository {

    public static List<Transaction> transactionList = new ArrayList<>();

    public Transaction save(Transaction transaction){
        transactionList.add(transaction);
        return transaction;
    }

    public List<Transaction> findAll() {
        return transactionList;
    }

    public List<Transaction> findLast10Transactions() {
        //write stream that sort the transactions based on creation date
        // and only return 10 of them
        return transactionList.stream()
                .sorted(Comparator.comparing(Transaction::getCreateDate).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }

    public List<Transaction> findTransactionListByAccountId(UUID id) {
        //if account id is used either as sender or receievr return those transactions
        return transactionList.stream()
                .filter(trans -> trans.getSender().equals(id) || trans.getReceiver().equals(id))
                .collect(Collectors.toList());

    }
}
