package com.ironhack.ivandavidtheironbank.account.model;

import com.ironhack.ivandavidtheironbank.account.dto.AccountDTO;
import com.ironhack.ivandavidtheironbank.user.model.AccountHolder;
import com.ironhack.ivandavidtheironbank.user.model.Admin;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@MappedSuperclass
public class Account {

    @Id
    @GeneratedValue()
    private Long id;

    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance;

    @ManyToOne()
    @JoinColumn(name = "primary_owner_id")
    private AccountHolder primaryOwner;

    @ManyToOne()
    @JoinColumn(name = "secondary_owner_id")
    private AccountHolder secondaryOwner;

/*
    @OneToMany(mappedBy = "account")
    private List<Transaction> transactionList;
*/

    public Account(String accountNumber, BigDecimal balance, AccountHolder primaryOwner, AccountHolder secondaryOwner) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
    }

    /*public static Account fromDTO(AccountDTO accountDTO) {
        var account = new Account();
        account.setBalance(accountDTO.getBalance());
        account.setPrimaryOwner(accountDTO.getPrimaryOwner());
        account.setSecondaryOwner(accountDTO.getSecondaryOwner());

        return account;
    }*/
}
