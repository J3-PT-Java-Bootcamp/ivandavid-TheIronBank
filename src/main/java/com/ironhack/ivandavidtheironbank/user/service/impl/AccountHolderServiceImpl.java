package com.ironhack.ivandavidtheironbank.user.service.impl;

import com.ironhack.ivandavidtheironbank.account.repository.SavingsAccountRepository;
import com.ironhack.ivandavidtheironbank.account.service.SavingsAccountService;
import com.ironhack.ivandavidtheironbank.user.dto.AccountHolderDTO;
import com.ironhack.ivandavidtheironbank.user.model.AccountHolder;
import com.ironhack.ivandavidtheironbank.user.repository.AccountHolderRepository;
import com.ironhack.ivandavidtheironbank.user.service.AccountHolderService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountHolderServiceImpl implements AccountHolderService {

    private final AccountHolderRepository accountHolderRepository;
    private final SavingsAccountService savingsAccountService;

    public AccountHolderServiceImpl(AccountHolderRepository accountHolderRepository, SavingsAccountService savingsAccountService) {
        this.accountHolderRepository = accountHolderRepository;
        this.savingsAccountService = savingsAccountService;
    }

    @Override
    public List<AccountHolderDTO> findAll() {
        var accountHolderList = accountHolderRepository.findAll();
        var accountHolderDTOList = new ArrayList<AccountHolderDTO>();

        if (accountHolderList.size() > 0) {
            for (AccountHolder accountHolder : accountHolderList) {
                var accountHolderDTO = AccountHolderDTO.fromEntity(accountHolder);
                accountHolderDTOList.add(accountHolderDTO);
            }
        }

        return accountHolderDTOList;
    }

    @Override
    public AccountHolderDTO getAccountHolderById(Long userSenderId) {
        var accountHolder = accountHolderRepository.findById(userSenderId).orElseThrow();
        return AccountHolderDTO.fromEntity(accountHolder);
    }

    @Override
    public AccountHolderDTO getAccountHolderByAccountNumber(String accountNumberReceiver) {
        var accountHolder = accountHolderRepository.getAccountHolderByAccountNumber(accountNumberReceiver);
        return AccountHolderDTO.fromEntity(accountHolder);
    }

    @Override
    public BigDecimal checkAccountBalance(Long accountId, String keycloakName) {
        var account = savingsAccountService.getSavingsAccountById(accountId);
        var primaryOwnerId = account.getPrimaryOwnerId();
        var primaryOwnerKeycloakId = accountHolderRepository.findById(primaryOwnerId).orElseThrow().getKeyckloakId();

        if (primaryOwnerKeycloakId.equals(keycloakName)) {
            return account.getBalance();
        }
        return null;
    }

}
