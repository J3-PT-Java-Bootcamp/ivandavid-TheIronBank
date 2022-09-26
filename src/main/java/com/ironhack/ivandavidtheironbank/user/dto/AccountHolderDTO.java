package com.ironhack.ivandavidtheironbank.user.dto;

import com.ironhack.ivandavidtheironbank.account.dto.SavingsAccountDTO;
import com.ironhack.ivandavidtheironbank.account.model.SavingsAccount;
import com.ironhack.ivandavidtheironbank.user.model.AccountHolder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountHolderDTO {

    private String name;
    private LocalDate dateOfBirth;

    //Address
    private Integer number;
    private String road;
    private String country;
    private Long postalCode;

    private String mailingAddress;
    private String keyckloakId;

    private String username;
    private String password;
    private String email;
    private String firstname;
    private String lastname;

    private List<SavingsAccountDTO> savingsAccountPrimaryOwnerDTOList;
    private List<SavingsAccountDTO> savingsAccountSecondaryOwnerDTOList;

    public static AccountHolderDTO fromEntity(AccountHolder accountHolder) {
        var accountHolderDTO = new AccountHolderDTO();
        accountHolderDTO.setName(accountHolder.getName());
        accountHolderDTO.setDateOfBirth(accountHolder.getDateOfBirth());
        accountHolderDTO.setMailingAddress(accountHolder.getMailingAddress());
        accountHolderDTO.setKeyckloakId(accountHolder.getKeyckloakId());

        if (accountHolder.getPrimaryAddress() != null) {
            accountHolderDTO.setNumber(accountHolder.getPrimaryAddress().getNumber());
            accountHolderDTO.setRoad(accountHolder.getPrimaryAddress().getRoad());
            accountHolderDTO.setCountry(accountHolder.getPrimaryAddress().getCountry());
            accountHolderDTO.setPostalCode(accountHolder.getPrimaryAddress().getPostalCode());
        }

        var savingsAccountPrimaryOwnerList = accountHolder.getSavingsAccountPrimaryOwnerList();
        var savingsAccountPrimaryOwnerDTOList = new ArrayList<SavingsAccountDTO>();
        if (savingsAccountPrimaryOwnerList != null) {
            for (SavingsAccount savingsAccount : savingsAccountPrimaryOwnerList) {
                var savingsAccountDTO = SavingsAccountDTO.fromEntity(savingsAccount);
                savingsAccountPrimaryOwnerDTOList.add(savingsAccountDTO);
            }
        }
        accountHolderDTO.setSavingsAccountPrimaryOwnerDTOList(savingsAccountPrimaryOwnerDTOList);

        var savingsAccountSecondaryOwnerList = accountHolder.getSavingsAccountSecondaryOwnerList();
        var savingsAccountSecondaryOwnerDTOList = new ArrayList<SavingsAccountDTO>();
        if (savingsAccountSecondaryOwnerList != null) {
            for (SavingsAccount savingsAccount : savingsAccountSecondaryOwnerList) {
                var savingsAccountDTO = SavingsAccountDTO.fromEntity(savingsAccount);
                savingsAccountSecondaryOwnerDTOList.add(savingsAccountDTO);
            }
        }
        accountHolderDTO.setSavingsAccountSecondaryOwnerDTOList(savingsAccountSecondaryOwnerDTOList);

        return accountHolderDTO;
    }

}
