package com.ironhack.ivandavidtheironbank.account.model;

import com.ironhack.ivandavidtheironbank.account.dto.SavingsAccountDTO;
import com.ironhack.ivandavidtheironbank.account.enums.StatusType;
import com.ironhack.ivandavidtheironbank.user.model.AccountHolder;
import com.ironhack.ivandavidtheironbank.user.model.Admin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "savings_account")
public class SavingsAccount extends DebitAccount {

    @Column(name = "minimum_balance", nullable = false)
    private BigDecimal minimumBalance;

    @Column(name = "interest_rate", nullable = false)
    private BigDecimal interestRate;

    public SavingsAccount(String accountNumber, BigDecimal balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey, StatusType statusType, BigDecimal penaltyFee, BigDecimal minimumBalance, BigDecimal interestRate) {
        super(accountNumber, balance, primaryOwner, secondaryOwner, secretKey, statusType, penaltyFee);
        this.minimumBalance = minimumBalance;
        this.interestRate = interestRate;
    }

    public static SavingsAccount fromDTO(SavingsAccountDTO savingsAccountDTO, AccountHolder primaryOwner, AccountHolder secondaryOwner) {
        var savingsAccount = new SavingsAccount();
        savingsAccount.setAccountNumber(savingsAccountDTO.getAccountNumber());
        savingsAccount.setMinimumBalance(savingsAccountDTO.getMinimumBalance());
        savingsAccount.setInterestRate(savingsAccountDTO.getInterestRate());
        savingsAccount.setBalance(savingsAccountDTO.getBalance());
        savingsAccount.setPrimaryOwner(primaryOwner);
        savingsAccount.setSecondaryOwner(secondaryOwner);
        savingsAccount.setSecretKey(savingsAccountDTO.getSecretKey());
        savingsAccount.setStatusType(savingsAccountDTO.getStatusType());
        savingsAccount.setPenaltyFee(savingsAccountDTO.getPenaltyFee());
        savingsAccount.setCreationDate(Instant.now());

        return savingsAccount;
    }

}
