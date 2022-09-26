package com.ironhack.ivandavidtheironbank.account.dto;

import com.ironhack.ivandavidtheironbank.account.enums.StatusType;
import com.ironhack.ivandavidtheironbank.account.model.SavingsAccount;
import com.ironhack.ivandavidtheironbank.user.dto.AccountHolderDTO;
import com.ironhack.ivandavidtheironbank.user.model.AccountHolder;
import com.ironhack.ivandavidtheironbank.user.model.Admin;
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
public class SavingsAccountDTO {

    private String accountNumber;
    private BigDecimal minimumBalance;
    private BigDecimal interestRate;

    private BigDecimal balance;
    private Long primaryOwnerId;   //private Long primaryOwner_id;
    private Long secondaryOwnerId; //private Long secondaryOwner_id;
    private String secretKey;
    private StatusType statusType;
    private BigDecimal penaltyFee;
    private Instant creationDate;

    public static SavingsAccountDTO fromEntity(SavingsAccount savingsAccount) {
        var savingsAccountDTO = new SavingsAccountDTO();
        savingsAccountDTO.setAccountNumber(savingsAccount.getAccountNumber());
        savingsAccountDTO.setMinimumBalance(savingsAccount.getMinimumBalance());
        savingsAccountDTO.setInterestRate(savingsAccount.getInterestRate());
        savingsAccountDTO.setBalance(savingsAccount.getBalance());
        savingsAccountDTO.setPrimaryOwnerId(savingsAccount.getPrimaryOwner().getId());
        savingsAccountDTO.setSecondaryOwnerId(savingsAccount.getSecondaryOwner().getId());
        savingsAccountDTO.setSecretKey(savingsAccount.getSecretKey());
        savingsAccountDTO.setStatusType(savingsAccount.getStatusType());
        savingsAccountDTO.setPenaltyFee(savingsAccount.getPenaltyFee());
        savingsAccountDTO.setCreationDate(savingsAccount.getCreationDate());

        return savingsAccountDTO;
    }

}
