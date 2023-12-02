package com.bahd.service.impl;

import com.bahd.dto.AccountDTO;
import com.bahd.dto.TransactionDTO;
import com.bahd.enums.AccountType;
import com.bahd.exception.AccountOwnershipException;
import com.bahd.exception.BadRequestException;
import com.bahd.exception.BalanceNotSufficientException;
import com.bahd.repository.AccountRepository;
import com.bahd.repository.TransactionRepository;
import com.bahd.service.TransactionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class TransactionServiceImpl implements TransactionService {

    @Value("${under_construction}")
    private boolean underConstruction;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public TransactionDTO makeTransfer(AccountDTO sender, AccountDTO receiver, BigDecimal amount, Date creationDate, String message) {

       if(!underConstruction) {
        /*
        if sender or receiver is null
        if sender & receiver the same account
        if sender has enough balance to make transfer
        if both accounts are checking, if not, one of them saving, it needs to be same userId
         */

           validateAccount(sender, receiver);
           checkAccountOwnership(sender, receiver);
           executeBalanceAndUpdateIfRequired(amount, sender, receiver);

        /*
        after all validations, money transferred, we need to create Transaction object and save/return
         */

           TransactionDTO transactionDTO = TransactionDTO.builder().amount(amount).sender(sender.getId()).receiver(receiver.getId())
                   .createDate(creationDate).message(message).build();

           return transactionRepository.save(transactionDTO);
       }else{
           throw new UnderConstructionException("App is under construction. Please try again later!");
       }
    }

    private void executeBalanceAndUpdateIfRequired(BigDecimal amount, AccountDTO sender, AccountDTO receiver) {
        if(checkSenderBalance(sender, amount)){
            //update sender and receiver balance
            sender.setBalance(sender.getBalance().subtract(amount));
            receiver.setBalance(receiver.getBalance().add(amount));
        }else{
            throw new BalanceNotSufficientException("Balance is not enough fro this transfer");
        }
    }

    private boolean checkSenderBalance(AccountDTO sender, BigDecimal amount) {
        //verify sender has enough balance to send
          return sender.getBalance().subtract(amount).compareTo(BigDecimal.ZERO)>=0 ;
      //  return sender.getBalance().compareTo(amount)>=0;
    }

    private void checkAccountOwnership(AccountDTO sender, AccountDTO receiver) {
        /*
        write an if statement that if one of the account is saving,
        and user of sender or receiver is not the same, throw AccountOwnershipException
         */
//        if(!sender.getAccountType().equals(receiver.getAccountType()) && sender.getUserId().equals(receiver.getUserId())){
//            throw new AccountOwnershipException("Account owner should be the same");
        if(sender.getAccountType().equals(AccountType.SAVING) || receiver.getAccountType().equals(AccountType.SAVING)
            && !sender.getUserId().equals(receiver.getUserId())) {
            throw new AccountOwnershipException("If one of the account is saving, user must be the same as sender & receiver");
        }
    }

    private void validateAccount(AccountDTO sender, AccountDTO receiver) {
        /*
        if any account is null
        if account ids are the same
        if the account exist in database
         */
        if(sender ==null || receiver== null){
            throw new BadRequestException("Sender or Receiver cannot be null");
        }

        if( sender.getId().equals(receiver.getId())){
            throw new BadRequestException("Sender and receiver accounts cannot be the same");
        }

        findAccountById(sender.getId());
        findAccountById(receiver.getId());

    }

    private void findAccountById(UUID id) {
        accountRepository.findById(id);
    }

    @Override
    public List<TransactionDTO> findAllTransaction() {
        return transactionRepository.findAll();
    }

    @Override
    public List<TransactionDTO> last10Transactions() {
        return transactionRepository.findLast10Transactions();

    }

    @Override
    public List<TransactionDTO> findTransactionById(UUID id) {
        return transactionRepository.findTransactionListByAccountId(id);
    }
}
