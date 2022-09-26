package com.ironhack.ivandavidtheironbank.account.dto;

import com.ironhack.ivandavidtheironbank.account.enums.StatusType;
import com.ironhack.ivandavidtheironbank.account.model.StudentCheckingAccount;
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
public class StudentCheckingAccountDTO {

    private String accountNumber;
    private BigDecimal balance;
    private AccountHolder primaryOwner;   //private Long primaryOwner_id;
    private AccountHolder secondaryOwner; //private Long secondaryOwner_id;
    private String secretKey;
    private StatusType statusType;
    private BigDecimal penaltyFee;

    public static StudentCheckingAccountDTO fromEntity(StudentCheckingAccount studentCheckingAccount) {
        var studentCheckingAccountDTO = new StudentCheckingAccountDTO();
        studentCheckingAccountDTO.setAccountNumber(studentCheckingAccountDTO.getAccountNumber());
        studentCheckingAccountDTO.setBalance(studentCheckingAccount.getBalance());
        studentCheckingAccountDTO.setPrimaryOwner(studentCheckingAccount.getPrimaryOwner());
        studentCheckingAccountDTO.setSecondaryOwner(studentCheckingAccount.getSecondaryOwner());
        studentCheckingAccountDTO.setSecretKey(studentCheckingAccount.getSecretKey());
        studentCheckingAccountDTO.setStatusType(studentCheckingAccount.getStatusType());
        studentCheckingAccountDTO.setPenaltyFee(studentCheckingAccount.getPenaltyFee());

        return studentCheckingAccountDTO;
    }

}
