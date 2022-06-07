package com.example.demo.model;


import com.example.demo.enums.Roles;
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

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Roles roles;

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
    private Set<ReactionPost> reactions;

    @JsonIgnore
    @OneToMany
    @Column(name = "banned_id")
    private Set<Banned> banned;

    @Column(name = "active")
    private String active;



//    @OneToOne
//    @JoinColumn(name = "community_id", unique = false, nullable = false)
//    private Community moderatesCommunity;


    public User(int userId, String username, String password, String email, String avatar, LocalDate registrationDate, String description, String displayName, Roles roles, String active) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.avatar = avatar;
        this.registrationDate = registrationDate;
        this.description = description;
        this.displayName = displayName;
        this.roles = roles;
        this.active = active;
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


}
