package com.smartwallet.smartwallet.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferMoneyDto {

    @NotNull(message = "Receiver email is required")
    @Email(message = "Invalid email format")
    private String receiverEmail;//to whom we are sending money

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be greater than 0")
    private BigDecimal amount;
}
