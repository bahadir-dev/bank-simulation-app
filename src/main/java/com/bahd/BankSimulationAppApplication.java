package com.bahd;

import com.bahd.dto.AccountDTO;
import com.bahd.enums.AccountType;
import com.bahd.service.AccountService;
import com.bahd.service.TransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.Date;

@SpringBootApplication
public class BankSimulationAppApplication {

    public static void main(String[] args) {

        ApplicationContext container = SpringApplication.run(BankSimulationAppApplication.class, args);

//        //get account service and transaction service beans
           AccountService accountService = container.getBean(AccountService.class);
          TransactionService transactionService = container.getBean(TransactionService.class);
//
//        //create 2 accounts sender and receiver
//            AccountDTO sender = accountService.createNewAccount(BigDecimal.valueOf(70), new Date(), AccountType.CHECKING, 2L);
//           AccountDTO receiver = accountService.createNewAccount(BigDecimal.valueOf(50), new Date(), AccountType.SAVING, 2L);
//        Account receiver2 =null;
//        accountService.listAllAccount().forEach(System.out::println);
//
         transactionService.makeTransfer(sender, receiver, new BigDecimal(40),new Date(), "Transaction 1");
//        System.out.println(transactionService.findAllTransaction().get(0));
//
//        accountService.listAllAccount().forEach(System.out::println);

        //create some transactions
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}
