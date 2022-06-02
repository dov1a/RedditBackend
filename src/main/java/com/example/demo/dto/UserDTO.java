package com.example.demo.dto;

import com.example.demo.enums.Roles;
import com.example.demo.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO implements Serializable {

    private int userId;

    @NotNull
    @Size(min = 6, max = 30)
    private String username;

    @NotNull
    @Min(8)
    private String password;

    @NotNull
    private String email;

    @NotNull
    private String avatar;

    private LocalDate registrationDate;

    @NotNull
    private String description;

    @NotNull
    private String displayName;

    private Roles roles;

    public UserDTO(User createdUser) {
        this.userId = createdUser.getUserId();
        this.username = createdUser.getUsername();
        this.password = createdUser.getPassword();
        this.email = createdUser.getEmail();
        this.avatar = createdUser.getAvatar();
        this.description = createdUser.getDescription();
        this.displayName = createdUser.getDisplayName();
        this.roles = createdUser.getRoles();
    }

}
