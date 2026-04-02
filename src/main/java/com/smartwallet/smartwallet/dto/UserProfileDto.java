package com.smartwallet.smartwallet.dto;

import lombok.Data;

import java.time.LocalDateTime;
//RESPONSE DTO
@Data
public class UserProfileDto {
    private Long id;
    private String name;
    private String email;
    private String phone_no;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
