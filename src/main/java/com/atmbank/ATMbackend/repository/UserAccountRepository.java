package com.atmbank.ATMbackend.repository;

import com.atmbank.ATMbackend.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {

    boolean existsByAccountNumber(String accountNumber);


    Optional<UserAccount> findByAccountNumber(String accountNumber);
}
