package com.ironhack.ivandavidtheironbank.account.controller.impl;

import com.ironhack.ivandavidtheironbank.account.controller.SavingsAccountController;
import com.ironhack.ivandavidtheironbank.account.dto.SavingsAccountDTO;
import com.ironhack.ivandavidtheironbank.account.service.SavingsAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/savingsaccounts")
@RequiredArgsConstructor
public class SavingsAccountControllerImpl implements SavingsAccountController {

    private final SavingsAccountService savingsAccountService;

    @Override
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public SavingsAccountDTO create(@RequestBody @Valid SavingsAccountDTO savingsAccountDTO) {
        var saDTO = savingsAccountService.create(savingsAccountDTO);
        return saDTO;
    }
}
