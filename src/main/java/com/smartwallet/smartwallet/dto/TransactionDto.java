package com.smartwallet.smartwallet.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
//RESPONSE DTO
@Data
public class TransactionDto {
    private String senderEmail;
    private String receiverEmail;
    private BigDecimal amount;
    private LocalDateTime createdAt;

}
