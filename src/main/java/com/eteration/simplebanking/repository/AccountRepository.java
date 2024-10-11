package com.eteration.simplebanking.repository;

import com.eteration.simplebanking.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    List<Account> findByAccountNumber(String accountNumber);
    Account findAllByAccountNumber(String accountNumber);
}
