package com.bahd.repository;

import com.bahd.dto.TransactionDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class TransactionRepository {

    public static List<TransactionDTO> transactionDTOList = new ArrayList<>();

    public TransactionDTO save(TransactionDTO transactionDTO){
        transactionDTOList.add(transactionDTO);
        return transactionDTO;
    }

    public List<TransactionDTO> findAll() {
        return transactionDTOList;
    }

    public List<TransactionDTO> findLast10Transactions() {
        //write stream that sort the transactions based on creation date
        // and only return 10 of them
        return transactionDTOList.stream()
                .sorted(Comparator.comparing(TransactionDTO::getCreateDate).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }

    public List<TransactionDTO> findTransactionListByAccountId(UUID id) {
        //if account id is used either as sender or receievr return those transactions
        return transactionDTOList.stream()
                .filter(trans -> trans.getSender().equals(id) || trans.getReceiver().equals(id))
                .collect(Collectors.toList());

    }
}
