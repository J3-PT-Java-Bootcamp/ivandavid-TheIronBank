package com.ironhack.ivandavidtheironbank.account.service;

import com.ironhack.ivandavidtheironbank.account.dto.SavingsAccountDTO;
import com.ironhack.ivandavidtheironbank.account.model.SavingsAccount;

import java.math.BigDecimal;

public interface SavingsAccountService {

    SavingsAccountDTO create(SavingsAccountDTO savingsAccountDTO);

    BigDecimal getBalanceByAccountNumber(String accountNumberSender);

    void updateBalanceByAccountNumber(String accountNumberSender, BigDecimal newBalanceSender);

    SavingsAccountDTO getSavingsAccountById(Long accountId);


}
