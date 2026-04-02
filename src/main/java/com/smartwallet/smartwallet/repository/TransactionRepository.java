package com.smartwallet.smartwallet.repository;

import com.smartwallet.smartwallet.model.Transaction;
import com.smartwallet.smartwallet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    // All transactions where user is sender
    List<Transaction> findBySender(User sender);

    // All transactions where user is receiver
    List<Transaction> findByReceiver(User receiver);
}
