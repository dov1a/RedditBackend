package com.example.demo.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;


@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "username", unique = false, nullable = false)
    private String username;

    @Column(name = "password", unique = false, nullable = false)
    private String password;

    @Column(name = "email", unique = false, nullable = false)
    private String email;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "registrationDate", unique = false, nullable = false)
    private LocalDate registrationDate;

    @Column(name = "description", unique = false, nullable = false)
    private String description;

    @Column(name = "displayName", unique = false, nullable = false)
    private String displayName;

    @Column(name = "userType", unique = false, nullable = false)
    private String userType;

    @JsonIgnore
    @ManyToMany
    @Column(name = "community_id")
    private Set<Community> communities;

    @JsonIgnore
    @OneToMany
    @Column(name = "post_id", unique = false, nullable = false)
    private Set<Post> posts;

    @JsonIgnore
    @OneToMany
    @Column(name = "comment_id", unique = false, nullable = false)
    private Set<Comment> comments;

    @JsonIgnore
    @OneToMany
    @Column(name = "reaction_id", unique = false, nullable = false)
    private Set<Reaction> reactions;

    @JsonIgnore
    @OneToMany
    @Column(name = "banned_id")
    private Set<Banned> banned;

//    @OneToOne
//    @JoinColumn(name = "community_id", unique = false, nullable = false)
//    private Community moderatesCommunity;


    public User(int userId, String username, String password, String email, String avatar, LocalDate registrationDate, String description, String displayName, String userType) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.avatar = avatar;
        this.registrationDate = registrationDate;
        this.description = description;
        this.displayName = displayName;
        this.userType = userType;
    }

    public User(){

    }



    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
