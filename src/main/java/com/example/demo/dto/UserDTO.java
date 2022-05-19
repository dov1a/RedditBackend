package com.example.demo.dto;

import com.example.demo.model.User;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO implements Serializable {
    private int userId;
    private String username;
    private String password;
    private String email;
    private String avatar;
    private LocalDate registrationDate;
    private String description;
    private String displayName;
    private String userType;

    public UserDTO(User createdUser) {
        this.userId = createdUser.getUserId();
        this.username = createdUser.getUsername();
        this.password = createdUser.getPassword();
        this.email = createdUser.getEmail();
        this.avatar = createdUser.getAvatar();
        this.registrationDate = createdUser.getRegistrationDate();
        this.description = createdUser.getDescription();
        this.displayName = createdUser.getDisplayName();
        this.userType = createdUser.getUserType();
    }

}
