package com.ironhack.ivandavidtheironbank.transaction.controller.impl;

import com.ironhack.ivandavidtheironbank.transaction.controller.TransactionController;
import com.ironhack.ivandavidtheironbank.transaction.dto.TransactionDTO;
import com.ironhack.ivandavidtheironbank.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionControllerImpl implements TransactionController {

    private final TransactionService transactionService;

    @Override
    @RequestMapping("/doTransaction")
    @ResponseStatus(HttpStatus.OK)
    public TransactionDTO doTransaction(@RequestBody @Valid TransactionDTO transactionDTO) {
        return transactionService.doTransaction(transactionDTO);
    }
}
