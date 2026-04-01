package com.smartwallet.smartwallet.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EditUserProfileDto {

    private String name;
    private String email;
    private String phone_no;
}
