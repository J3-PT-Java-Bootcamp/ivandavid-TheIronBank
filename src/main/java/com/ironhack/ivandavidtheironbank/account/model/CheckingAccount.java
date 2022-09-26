package com.ironhack.ivandavidtheironbank.account.model;

import com.ironhack.ivandavidtheironbank.account.enums.StatusType;
import com.ironhack.ivandavidtheironbank.user.model.AccountHolder;
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
@Table(name = "checking_account")
public class CheckingAccount extends DebitAccount {

    @Column(name = "monthly_maintenance_fee", nullable = false)
    private BigDecimal monthlyMaintenanceFee;

    @Column(name = "minimum_balance", nullable = false)
    private BigDecimal minimumBalance;

    public CheckingAccount(String accountNumber, BigDecimal balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey, StatusType statusType, BigDecimal penaltyFee, BigDecimal monthlyMaintenanceFee, BigDecimal minimumBalance) {
        super(accountNumber, balance, primaryOwner, secondaryOwner, secretKey, statusType, penaltyFee);
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
        this.minimumBalance = minimumBalance;
    }
}
