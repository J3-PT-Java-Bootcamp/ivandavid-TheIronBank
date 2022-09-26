package com.ironhack.ivandavidtheironbank.transaction.service;

import com.ironhack.ivandavidtheironbank.account.service.SavingsAccountService;
import com.ironhack.ivandavidtheironbank.transaction.dto.TransactionDTO;
import com.ironhack.ivandavidtheironbank.transaction.model.Transaction;
import com.ironhack.ivandavidtheironbank.transaction.repository.TransactionRepository;
import com.ironhack.ivandavidtheironbank.user.model.AccountHolder;
import com.ironhack.ivandavidtheironbank.user.service.AccountHolderService;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    private final AccountHolderService accountHolderService;
    private final SavingsAccountService savingsAccountService;


    public TransactionServiceImpl(TransactionRepository transactionRepository, AccountHolderService accountHolderService, SavingsAccountService savingsAccountService) {
        this.transactionRepository = transactionRepository;
        this.accountHolderService = accountHolderService;
        this.savingsAccountService = savingsAccountService;
    }

    @Override
    public TransactionDTO doTransaction(TransactionDTO transactionDTO) {

        //Sender
        var accountNumberSender = transactionDTO.getAccountNumberSender();
        var balanceSender = savingsAccountService.getBalanceByAccountNumber(accountNumberSender);

        var amountTosend = transactionDTO.getAmount();

        //Receiver
        var accountNumberReceiver = transactionDTO.getAccountNumberReceiver();
        var balanceReceiver = savingsAccountService.getBalanceByAccountNumber(accountNumberReceiver);

        var newBalanceSender = balanceSender.subtract(amountTosend);
        var newBalanceReceiver = balanceReceiver.add(amountTosend);

        savingsAccountService.updateBalanceByAccountNumber(accountNumberSender, newBalanceSender);
        savingsAccountService.updateBalanceByAccountNumber(accountNumberReceiver, newBalanceReceiver);

        var transaction = new Transaction();
        transaction.setUserSenderId(transactionDTO.getUserSenderId());
        transaction.setAccountNumberSender(accountNumberSender);
        transaction.setAccountNumberReceiver(accountNumberReceiver);
        transaction.setConcept(transactionDTO.getConcept());
        transaction.setReceiverName(transactionDTO.getReceiverName());
        transaction.setAmount(transactionDTO.getAmount());

        transactionRepository.save(transaction);

        //sendTransaction();
        //bothUsersExist();
        return null;
    }

    private boolean bothUsersExist() {
        return true;
    }

}
