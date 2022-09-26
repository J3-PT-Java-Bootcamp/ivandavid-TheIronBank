package com.ironhack.ivandavidtheironbank.account.service;

import com.ironhack.ivandavidtheironbank.account.dto.SavingsAccountDTO;
import com.ironhack.ivandavidtheironbank.account.model.SavingsAccount;
import com.ironhack.ivandavidtheironbank.account.repository.SavingsAccountRepository;
import com.ironhack.ivandavidtheironbank.user.repository.AccountHolderRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import java.math.BigDecimal;

@Service
//@RequiredArgsConstructor
public class SavingsAccountServiceImpl implements SavingsAccountService {

    private final SavingsAccountRepository savingsAccountRepository;
    private final AccountHolderRepository accountHolderRepository;


    public SavingsAccountServiceImpl(SavingsAccountRepository savingsAccountRepository, AccountHolderRepository accountHolderRepository) {
        this.savingsAccountRepository = savingsAccountRepository;
        this.accountHolderRepository = accountHolderRepository;
    }

    @Override
    public SavingsAccountDTO create(SavingsAccountDTO savingsAccountDTO) {

        var accountHolderPrimaryOwner = accountHolderRepository.findById(savingsAccountDTO.getPrimaryOwnerId()).get();
        var accountHolderSecondaryOwner = accountHolderRepository.findById(savingsAccountDTO.getSecondaryOwnerId()).get();

        var savingsAccount = SavingsAccount.fromDTO(savingsAccountDTO, accountHolderPrimaryOwner, accountHolderSecondaryOwner);

        var storedSavingsAccount = savingsAccountRepository.save(savingsAccount);
        var saDTO = SavingsAccountDTO.fromEntity(storedSavingsAccount);
        return saDTO;
        //return null;
    }


    @Override
    public BigDecimal getBalanceByAccountNumber(String accountNumberSender) {
        var balance = savingsAccountRepository.getBalanceByAccountNumber(accountNumberSender);
        return balance;
    }

    @Override
    public void updateBalanceByAccountNumber(String accountNumberSender, BigDecimal newBalanceSender) {
        savingsAccountRepository.updateBalanceByAccountNumber(newBalanceSender, accountNumberSender);
    }

    @Override
    public SavingsAccountDTO getSavingsAccountById(Long accountId) {
        var savingsAccount = savingsAccountRepository.findById(accountId).orElseThrow();
        return SavingsAccountDTO.fromEntity(savingsAccount);
    }

}
