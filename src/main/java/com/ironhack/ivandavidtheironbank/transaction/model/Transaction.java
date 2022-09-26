package com.ironhack.ivandavidtheironbank.transaction.model;

import com.ironhack.ivandavidtheironbank.transaction.dto.TransactionDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_sender_id")
    private Long userSenderId;

    @Column(name = "account_number_sender")
    private String accountNumberSender;

    @Column(name = "account_number_receiver")
    private String accountNumberReceiver;

    @Column(name = "receiver")
    private String receiverName;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "concept")
    private String concept;

    public static Transaction fromDTO(TransactionDTO transactionDTO) {
        var transaction = new Transaction();
        transaction.setUserSenderId(transactionDTO.getUserSenderId());
        transaction.setAccountNumberSender(transactionDTO.getAccountNumberSender());
        transaction.setAccountNumberReceiver(transactionDTO.getAccountNumberReceiver());
        transaction.setReceiverName(transactionDTO.getReceiverName());
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setConcept(transactionDTO.getConcept());
        return transaction;
    }

}
