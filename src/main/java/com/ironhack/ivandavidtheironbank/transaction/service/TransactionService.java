package com.ironhack.ivandavidtheironbank.transaction.service;

import com.ironhack.ivandavidtheironbank.transaction.dto.TransactionDTO;

public interface TransactionService {

    TransactionDTO doTransaction(TransactionDTO transactionDTO);

}
