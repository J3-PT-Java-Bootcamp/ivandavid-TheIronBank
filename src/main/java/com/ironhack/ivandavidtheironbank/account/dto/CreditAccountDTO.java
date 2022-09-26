package com.ironhack.ivandavidtheironbank.account.dto;

import com.ironhack.ivandavidtheironbank.account.enums.StatusType;
import com.ironhack.ivandavidtheironbank.account.model.CreditAccount;
import com.ironhack.ivandavidtheironbank.account.model.SavingsAccount;
import com.ironhack.ivandavidtheironbank.user.model.AccountHolder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreditAccountDTO {

    private String accountNumber;
    private BigDecimal creditLimit;
    private BigDecimal interestRate;
    private BigDecimal balance;
    private AccountHolder primaryOwner;   //private Long primaryOwner_id;
    private AccountHolder secondaryOwner; //private Long secondaryOwner_id;

    public static CreditAccountDTO fromEntity(CreditAccount creditAccount) {
        var creditAccountDTO = new CreditAccountDTO();
        creditAccountDTO.setAccountNumber(creditAccount.getAccountNumber());
        creditAccountDTO.setCreditLimit(creditAccount.getCreditLimit());
        creditAccountDTO.setInterestRate(creditAccount.getInterestRate());
        creditAccountDTO.setBalance(creditAccount.getBalance());
        creditAccountDTO.setPrimaryOwner(creditAccount.getPrimaryOwner());
        creditAccountDTO.setSecondaryOwner(creditAccount.getSecondaryOwner());

        return creditAccountDTO;
    }

}
