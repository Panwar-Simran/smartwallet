package com.smartwallet.smartwallet.controller;

import com.smartwallet.smartwallet.dto.EditUserProfileDto;
import com.smartwallet.smartwallet.dto.UserProfileDto;
import com.smartwallet.smartwallet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<UserProfileDto> getProfile(Principal principal) {
        // principal.getName() gives the email(since unique) from JWT
        UserProfileDto profile = userService.getProfile(principal.getName());
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }


    @PutMapping("/profile")
    public ResponseEntity<UserProfileDto> updateProfile(
            Principal principal, @RequestBody EditUserProfileDto dto) {
        UserProfileDto updatedProfile = userService.updateProfile(principal.getName(), dto);
        return new ResponseEntity<>(updatedProfile, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserProfileDto>> searchUser(
            @RequestParam String query, Principal principal) {

        List<UserProfileDto> users = userService.searchUsers(query, principal.getName());
        return new ResponseEntity <>(users,HttpStatus.OK);
    }
}
