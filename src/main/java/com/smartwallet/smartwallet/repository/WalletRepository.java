package com.smartwallet.smartwallet.repository;

import com.smartwallet.smartwallet.model.User;
import com.smartwallet.smartwallet.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet,Long> {
    Optional<Wallet> findByUser(User user);

}
