package com.ironhack.ivandavidtheironbank.account.dto;

import com.ironhack.ivandavidtheironbank.account.enums.StatusType;
import com.ironhack.ivandavidtheironbank.account.model.CheckingAccount;
import com.ironhack.ivandavidtheironbank.account.model.SavingsAccount;
import com.ironhack.ivandavidtheironbank.user.model.AccountHolder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CheckingAccountDTO {

    private String accountNumber;
    private BigDecimal monthlyMaintenanceFee;
    private BigDecimal minimumBalance;

    private BigDecimal balance;
    private AccountHolder primaryOwner;   //private Long primaryOwner_id;
    private AccountHolder secondaryOwner; //private Long secondaryOwner_id;
    private String secretKey;
    private StatusType statusType;
    private BigDecimal penaltyFee;

    public static CheckingAccountDTO fromEntity(CheckingAccount checkingAccount) {
        var checkingAccountDTO = new CheckingAccountDTO();
        checkingAccountDTO.setAccountNumber(checkingAccount.getAccountNumber());
        checkingAccountDTO.setMonthlyMaintenanceFee(checkingAccount.getMonthlyMaintenanceFee());
        checkingAccountDTO.setBalance(checkingAccount.getBalance());
        checkingAccountDTO.setPrimaryOwner(checkingAccount.getPrimaryOwner());
        checkingAccountDTO.setSecondaryOwner(checkingAccount.getSecondaryOwner());
        checkingAccountDTO.setSecretKey(checkingAccount.getSecretKey());
        checkingAccountDTO.setStatusType(checkingAccount.getStatusType());
        checkingAccountDTO.setPenaltyFee(checkingAccount.getPenaltyFee());

        return checkingAccountDTO;
    }

}
