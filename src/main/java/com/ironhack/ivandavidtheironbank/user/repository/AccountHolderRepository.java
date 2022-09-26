package com.ironhack.ivandavidtheironbank.user.repository;

import com.ironhack.ivandavidtheironbank.user.dto.AccountHolderDTO;
import com.ironhack.ivandavidtheironbank.user.model.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface AccountHolderRepository extends JpaRepository<AccountHolder, Long> {

    //select ac.name, sa.account_number from account_holder ac
    //    join savings_account sa on ac.id = sa.primary_owner_id
    //    where account_number = 'QQQPPP444';
    //@Query("SELECT ac.name, sa.accountNumber FROM AccountHolder ac JOIN SavingsAccount sa ON ac.id = sa.primaryOwner.id WHERE sa.accountNumber =: accountNumber")
    //              select ac.name, sa.account_number, sa.balance from account_holder ac join savings_account sa on ac.id = sa.primary_owner_id where account_number = 'YYYRRR666';
    //@Query("SELECT ac.name, sa.accountNumber, sa.balance FROM AccountHolder ac JOIN SavingsAccount sa ON ac.id = sa.primaryOwner.id WHERE sa.accountNumber =: accountNumber")

    //@Query(value = "select sa.balance from account_holder ac join savings_account sa on ac.id = sa.primary_owner_id where account_number = :accountNumber", nativeQuery = true)
    //BigDecimal getAccountHolderBalanceByAccountNumber(@Param("accountNumber") String accountNumber);

    //@Query("SELECT sa.balance FROM AccountHolder ah JOIN SavingsAccount sa ON ah.id = sa.primaryOwner.id WHERE sa.accountNumber = :accountNumber")
    //BigDecimal getAccountHolderBalanceByIdAndAccountListId(@Param(""), @Param("accountNumber") String accountNumber);


    //@Query("SELECT ah.id, ah.dateOfBirth, ah.mailingAddress, ah.name, ah.savingsAccountPrimaryOwnerList, ah.savingsAccountSecondaryOwnerList FROM AccountHolder ah JOIN SavingsAccount sa ON ah.id = sa.primaryOwner.id WHERE sa.accountNumber = :accountNumber")
    @Query(value = "select ah.id, ah.name, ah.date_of_birth, ah.mailing_address, ah.country, ah.number, ah.postal_code, ah.road from account_holder ah join savings_account sa on ah.id = sa.primary_owner_id where account_number = :accountNumber", nativeQuery = true)
    AccountHolder getAccountHolderByAccountNumber(@Param("accountNumber") String accountNumber);

    @Query(value = "select balance from savings_account where account_number = :accountNumber and primary_owner_id = :id", nativeQuery = true)
    BigDecimal getBalanceByAccountNumber(String accountNumber, Long id);

}
