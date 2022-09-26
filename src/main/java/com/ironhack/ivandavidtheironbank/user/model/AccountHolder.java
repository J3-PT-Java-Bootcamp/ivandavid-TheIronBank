package com.ironhack.ivandavidtheironbank.user.model;

import com.ironhack.ivandavidtheironbank.account.model.*;
import com.ironhack.ivandavidtheironbank.user.dto.AccountHolderDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "account_holder")
public class AccountHolder extends User {

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    public AccountHolder(String name, LocalDate dateOfBirth) {
        super(name);
        this.dateOfBirth = dateOfBirth;
    }

    @Embedded
    @Column(name = "primary_address")
    private Address primaryAddress;

    @Column(name = "mailing_address")
    @Email(message = "primaryAddress must have an email address format")
    private String mailingAddress;

    @Column(name = "keycloak_id", nullable = false)
    private String keyckloakId;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "primaryOwner")
    @Nullable
    private List<SavingsAccount> savingsAccountPrimaryOwnerList;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "secondaryOwner")
    @Fetch(value = FetchMode.SUBSELECT)
    @Nullable
    private List<SavingsAccount> savingsAccountSecondaryOwnerList;

    /*public AccountHolder(String name, LocalDate dateOfBirth, Address primaryAddress, String mailingAddress, List<SavingsAccount> savingsAccountPrimaryOwnerList, List<SavingsAccount> savingsAccountSecondaryOwnerList) {
        super(name);
        this.dateOfBirth = dateOfBirth;
        this.primaryAddress = primaryAddress;
        this.mailingAddress = mailingAddress;
        this.savingsAccountPrimaryOwnerList = savingsAccountPrimaryOwnerList;
        this.savingsAccountSecondaryOwnerList = savingsAccountSecondaryOwnerList;
    }*/

    public AccountHolder(String name, LocalDate dateOfBirth, Address primaryAddress, String mailingAddress, String keyckloakId, @Nullable List<SavingsAccount> savingsAccountPrimaryOwnerList, @Nullable List<SavingsAccount> savingsAccountSecondaryOwnerList) {
        super(name);
        this.dateOfBirth = dateOfBirth;
        this.primaryAddress = primaryAddress;
        this.mailingAddress = mailingAddress;
        this.keyckloakId = keyckloakId;
        this.savingsAccountPrimaryOwnerList = savingsAccountPrimaryOwnerList;
        this.savingsAccountSecondaryOwnerList = savingsAccountSecondaryOwnerList;
    }

    public static AccountHolder fromDTO(AccountHolderDTO accountHolderDTO) {
        var accountHolder = new AccountHolder();
        accountHolder.setName(accountHolderDTO.getName());
        accountHolder.setDateOfBirth(accountHolderDTO.getDateOfBirth());
        accountHolder.setMailingAddress(accountHolderDTO.getMailingAddress());
        accountHolder.setKeyckloakId(accountHolderDTO.getKeyckloakId());

        var address = new Address();
        if (accountHolderDTO.getNumber() != null) address.setNumber(accountHolderDTO.getNumber());
        if (accountHolderDTO.getRoad() != null) address.setRoad(accountHolderDTO.getRoad());
        if (accountHolderDTO.getCountry() != null) address.setCountry(accountHolderDTO.getCountry());
        if (accountHolderDTO.getPostalCode() != null) address.setPostalCode(accountHolderDTO.getPostalCode());
        accountHolder.setPrimaryAddress(address);

        /*var savingsAccountPrimaryOwnerDTOList = accountHolderDTO.getSavingsAccountPrimaryOwnerDTOList();
        var savingsAccountPrimaryOwnerList = new ArrayList<SavingsAccount>();
        if (savingsAccountPrimaryOwnerDTOList != null) {
            for (SavingsAccountDTO savingsAccountDTO : savingsAccountPrimaryOwnerDTOList) {
                savingsAccountDTO.getp
                var savingsAccount = SavingsAccount.fromDTO(savingsAccountDTO, AccountHolder.fromDTO(accountHolderDTO), AccountHolder.fromDTO(accountHolderDTO));
                //SavingsAccountDTO savingsAccountDTO, AccountHolder primaryOwner, AccountHolder secondaryOwner
                savingsAccountPrimaryOwnerList.add(savingsAccount);
            }
        }
        accountHolder.setSavingsAccountPrimaryOwnerList(savingsAccountPrimaryOwnerList);

        var savingsAccountSecondaryOwnerDTOList = accountHolderDTO.getSavingsAccountSecondaryOwnerDTOList();
        var savingsAccountSecondaryOwnerList = new ArrayList<SavingsAccount>();
        if (savingsAccountSecondaryOwnerDTOList != null) {
            for (SavingsAccountDTO savingsAccountDTO : savingsAccountSecondaryOwnerDTOList) {
                var savingsAccount = SavingsAccount.fromDTO(savingsAccountDTO);
                savingsAccountSecondaryOwnerList.add(savingsAccount);
            }
        }
        accountHolder.setSavingsAccountSecondaryOwnerList(savingsAccountSecondaryOwnerList);

         */

        return accountHolder;
    }
}
