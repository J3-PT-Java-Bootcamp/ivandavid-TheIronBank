package com.ironhack.ivandavidtheironbank.account.model;

import com.ironhack.ivandavidtheironbank.account.enums.StatusType;
import com.ironhack.ivandavidtheironbank.user.model.AccountHolder;
import com.ironhack.ivandavidtheironbank.user.model.Admin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
public class DebitAccount extends Account {

    @Column(name = "secret_key", nullable = false)
    private String secretKey;

    @CreatedDate
    @Column(name = "creation_date" , nullable = false/*, columnDefinition = "TIMESTAMP DEFAULT 2022-09-17T07:57:15.287+00:00"*/)
    private Instant creationDate;

    @Column(name = "status_type" , nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusType statusType;

    @Column(name = "penalty_fee", nullable = false)
    private BigDecimal penaltyFee;

    public DebitAccount(String accountNumber, BigDecimal balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey, StatusType statusType, BigDecimal penaltyFee) {
        super(accountNumber, balance, primaryOwner, secondaryOwner);
        this.secretKey = secretKey;
        this.creationDate = Instant.now();
        this.statusType = statusType;
        this.penaltyFee = penaltyFee;
    }
}
