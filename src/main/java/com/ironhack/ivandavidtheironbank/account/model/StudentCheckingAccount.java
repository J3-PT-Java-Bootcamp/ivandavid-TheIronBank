package com.ironhack.ivandavidtheironbank.account.model;

import com.ironhack.ivandavidtheironbank.account.dto.StudentCheckingAccountDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "student_checking_account")
public class StudentCheckingAccount extends DebitAccount {

    /*public static StudentCheckingAccount fromDTO(StudentCheckingAccountDTO studentCheckingAccountDTO) {
        var studentCheckingAccount = new StudentCheckingAccount();
        studentCheckingAccount.setBalance(studentCheckingAccountDTO.getBalance());
        studentCheckingAccount.setPrimaryOwner(studentCheckingAccountDTO.getPrimaryOwner());
        studentCheckingAccount.setSecondaryOwner(studentCheckingAccountDTO.getSecondaryOwner());
        studentCheckingAccount.setSecretKey(studentCheckingAccountDTO.getSecretKey());
        studentCheckingAccount.setCreationDate(studentCheckingAccountDTO.getCreationDate());
        studentCheckingAccount.setStatusType(studentCheckingAccountDTO.getStatusType());
        studentCheckingAccount.setPenaltyFee(studentCheckingAccountDTO.getPenaltyFee());

        return studentCheckingAccount;
    }*/

}
