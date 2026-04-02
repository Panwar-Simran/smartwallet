package com.smartwallet.smartwallet.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="transactions")
@Data

public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    //sender
    //Many transactions can be done by one user
    @ManyToOne
    @JoinColumn(name="sender_id", nullable = false)
    @NotNull(message = "Sender is required!")
    private User sender;


    //receiver
    @ManyToOne
    @JoinColumn(name="receiver_id",nullable = false)
    @NotNull(message = "Receiver is required!")
    private User receiver;

    //Amount
    @NotNull(message = "Amount is required!")
    @Positive(message = "Amount must be greater than 0")
    private BigDecimal amount;


    //Transaction time
    @NotNull(message = "Transaction time is required")
    LocalDateTime createdAt;
}
