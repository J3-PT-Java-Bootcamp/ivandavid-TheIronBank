package com.ironhack.ivandavidtheironbank.transaction.dto;

import com.ironhack.ivandavidtheironbank.transaction.model.Transaction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {

    private Long userSenderId;
    private String accountNumberSender;
    private String accountNumberReceiver;
    private String receiverName;
    private BigDecimal amount;
    private String concept;

    public static TransactionDTO fromEntity(Transaction transaction) {
        var transactionDTO = new TransactionDTO();
        transactionDTO.setUserSenderId(transaction.getUserSenderId());
        transactionDTO.setAccountNumberSender(transaction.getAccountNumberSender());
        transactionDTO.setAccountNumberReceiver(transaction.getAccountNumberReceiver());
        transactionDTO.setReceiverName(transaction.getReceiverName());
        transactionDTO.setAmount(transaction.getAmount());
        transactionDTO.setConcept(transaction.getConcept());
        return transactionDTO;
    }

}
