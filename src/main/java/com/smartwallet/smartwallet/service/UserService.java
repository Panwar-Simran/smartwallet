package com.smartwallet.smartwallet.service;

import com.smartwallet.smartwallet.dto.EditUserProfileDto;
import com.smartwallet.smartwallet.dto.UserProfileDto;
import com.smartwallet.smartwallet.exception.ResourceNotFoundException;
import com.smartwallet.smartwallet.model.User;
import com.smartwallet.smartwallet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    public UserProfileDto getProfile(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found!"));

        // Convert User entity to UserProfileDto
        UserProfileDto dto = new UserProfileDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPhone_no(user.getPhone_no());
        dto.setCreatedAt(user.getCreatedAt());

        return dto;

    }

    public UserProfileDto updateProfile(String email, EditUserProfileDto dto) {
        User user=userRepository.findByEmail(email)
                .orElseThrow(()->new ResourceNotFoundException("User not found!"));

        // Update editable fields
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPhone_no(dto.getPhone_no());
        user.setUpdatedAt(LocalDateTime.now()); // set updatedAt in service

        userRepository.save(user);

        // Convert to DTO to return
        UserProfileDto updatedDto = new UserProfileDto();
        updatedDto.setId(user.getId());
        updatedDto.setName(user.getName());
        updatedDto.setEmail(user.getEmail());
        updatedDto.setPhone_no(user.getPhone_no());
        updatedDto.setCreatedAt(user.getCreatedAt());
        updatedDto.setUpdatedAt(user.getUpdatedAt());
        return updatedDto;
    }


    public List<UserProfileDto> searchUsers(String query, String loggedInUserEmail) {
        // Fetch users matching name or email
        List<User> users = userRepository
                .findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(query, query);

        // Convert to DTO and exclude logged-in user
        List<UserProfileDto> result = new ArrayList<>();
        for (User user : users) {
            if (!user.getEmail().equalsIgnoreCase(loggedInUserEmail)) {
                UserProfileDto dto = new UserProfileDto();
                dto.setId(user.getId());
                dto.setName(user.getName());
                dto.setEmail(user.getEmail());
                result.add(dto);
            }
        }
        return result;
    }
}
