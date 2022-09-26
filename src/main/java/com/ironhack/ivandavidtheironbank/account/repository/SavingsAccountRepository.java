package com.ironhack.ivandavidtheironbank.account.repository;

import com.ironhack.ivandavidtheironbank.account.dto.SavingsAccountDTO;
import com.ironhack.ivandavidtheironbank.account.model.SavingsAccount;
import com.ironhack.ivandavidtheironbank.transaction.dto.TransactionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Repository
public interface SavingsAccountRepository extends JpaRepository<SavingsAccount, Long> {

    @Modifying
    @Transactional
    @Query(value = "update savings_account set balance = :newBalanceSender where account_number = :accountNumberSender", nativeQuery = true)
    int updateBalanceByAccountNumber(BigDecimal newBalanceSender, String accountNumberSender);

    @Query(value = "select balance from savings_account where account_number = :accountNumberSender", nativeQuery = true)
    BigDecimal getBalanceByAccountNumber(String accountNumberSender);

    @Query(value = "select id from savings_account where account_number = :accountNumber", nativeQuery = true)
    SavingsAccountDTO findSavingsAccountByAccountNumber(String accountNumber);

}
