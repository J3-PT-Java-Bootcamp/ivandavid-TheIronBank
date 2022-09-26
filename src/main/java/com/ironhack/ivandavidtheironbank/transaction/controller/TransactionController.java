package com.ironhack.ivandavidtheironbank.transaction.controller;

import com.ironhack.ivandavidtheironbank.transaction.dto.TransactionDTO;


public interface TransactionController {

    TransactionDTO doTransaction(TransactionDTO transactionDTO);

}
