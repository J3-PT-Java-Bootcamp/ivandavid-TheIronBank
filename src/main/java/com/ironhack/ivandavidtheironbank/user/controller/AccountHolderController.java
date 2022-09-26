package com.ironhack.ivandavidtheironbank.user.controller;

import com.ironhack.ivandavidtheironbank.user.dto.AccountHolderDTO;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

public interface AccountHolderController {

    AccountHolderDTO create(AccountHolderDTO accountHolderDTO) throws InterruptedException;

    List<AccountHolderDTO> findAll();

    ResponseEntity<BigDecimal> checkAccountBalance(Long accountId, Principal principal );

}
