package com.smartwallet.smartwallet.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    private String phone_no;
    private String role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
