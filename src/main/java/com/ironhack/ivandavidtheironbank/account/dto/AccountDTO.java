package com.ironhack.ivandavidtheironbank.account.dto;

import com.ironhack.ivandavidtheironbank.account.model.Account;
import com.ironhack.ivandavidtheironbank.user.model.AccountHolder;
import com.ironhack.ivandavidtheironbank.user.model.Admin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {

    @NotNull(message = "Balance may not be null")
    private BigDecimal balance;

    @NotNull(message = "Primary owner may not be null")
    private AccountHolder primaryOwner;

    private AccountHolder secondaryOwner;

    public static AccountDTO fromEntity(Account account) {
        var accountDTO = new AccountDTO();
        accountDTO.setBalance(account.getBalance());
        accountDTO.setPrimaryOwner(account.getPrimaryOwner());
        accountDTO.setSecondaryOwner(account.getSecondaryOwner());

        return accountDTO;
    }

}
