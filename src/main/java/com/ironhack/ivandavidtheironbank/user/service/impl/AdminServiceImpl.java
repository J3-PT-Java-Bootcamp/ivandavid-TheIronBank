package com.ironhack.ivandavidtheironbank.user.service.impl;

import com.ironhack.ivandavidtheironbank.account.service.SavingsAccountService;
import com.ironhack.ivandavidtheironbank.user.repository.AccountHolderRepository;
import com.ironhack.ivandavidtheironbank.user.repository.AdminRepository;
import com.ironhack.ivandavidtheironbank.user.service.AdminService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final SavingsAccountService savingsAccountService;

    public AdminServiceImpl(AdminRepository adminRepository, SavingsAccountService savingsAccountService) {
        this.adminRepository = adminRepository;
        this.savingsAccountService = savingsAccountService;
    }

    @Override
    public BigDecimal checkAccountBalance(Long accountId, Long adminId, String keycloakName) {

        var account = savingsAccountService.getSavingsAccountById(accountId);
        var adminKeycloakId = adminRepository.findById(adminId).orElseThrow().getKeyckloakId();

        if (adminKeycloakId.equals(keycloakName)) {
            return account.getBalance();
        }
        return null;
    }

}
