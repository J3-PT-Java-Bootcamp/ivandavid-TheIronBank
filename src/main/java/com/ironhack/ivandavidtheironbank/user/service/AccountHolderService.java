package com.ironhack.ivandavidtheironbank.user.service;

import com.ironhack.ivandavidtheironbank.user.dto.AccountHolderDTO;

import java.math.BigDecimal;
import java.util.List;

public interface AccountHolderService {

    List<AccountHolderDTO> findAll();

    AccountHolderDTO getAccountHolderById(Long userSenderId);

    AccountHolderDTO getAccountHolderByAccountNumber(String accountNumberReceiver);

    BigDecimal checkAccountBalance(Long accountId, String name);
}
