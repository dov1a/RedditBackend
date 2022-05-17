package com.example.demo.model;


import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;


@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true, nullable = false)
    private int userId;

    @Column(name = "username", unique = false, nullable = false)
    private String username;

    @Column(name = "password", unique = false, nullable = false)
    private String password;

    @Column(name = "email", unique = false, nullable = false)
    private String email;

    @Column(name = "avatar", unique = false, nullable = false)
    private String avatar;

    @Column(name = "registrationDate", unique = false, nullable = false)
    private LocalDate registrationDate;

    @Column(name = "description", unique = false, nullable = false)
    private String description;

    @Column(name = "displayName", unique = false, nullable = false)
    private String displayName;

    @Column(name = "userType", unique = false, nullable = false)
    private String userType;

    @ManyToMany
    @Column(name = "communities", unique = false, nullable = false)
    private Set<Community> communities;

    @OneToMany
    @Column(name = "posts", unique = false, nullable = false)
    private Set<Post> posts;

    @OneToMany
    @Column(name = "comments", unique = false, nullable = false)
    private Set<Comment> comments;

    @OneToMany
    @Column(name = "reactions", unique = false, nullable = false)
    private Set<Reaction> reactions;

    @OneToMany
    @Column(name = "banneds", unique = false, nullable = false)
    private Set<Banned> banneds;

    @OneToOne
    @Column(name = "moderates", unique = false, nullable = false)
    private Community moderatesCommunity;









}
