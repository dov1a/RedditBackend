package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;
import java.util.HashSet;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "community_id", unique = true, nullable = false)
    private int communityId;

    @Column(name = "name", unique = false, nullable = false)
    private String name;

    @Column(name = "description", unique = false, nullable = false)
    private String description;

    @Column(name = "creationDate", unique = false, nullable = false)
    private LocalDate creationDate;

    @Column(name = "isSuspend", unique = false, nullable = false)
    private boolean  isSuspend;

    @Column(name = "suspendedReason", unique = false, nullable = false)
    private String  suspendedReason;

    @ManyToMany
    @JoinColumn(name = "flairs")
    private Set<Flair> flairs;

    @OneToMany
    @JoinColumn(name = "rules")
    private Set<Rule> rules;

    @OneToMany
    @JoinColumn(name = "posts")
    private Set<Post> posts;

    @OneToMany
    @JoinColumn(name = "banned")
    private Set<Banned> banned;

    @ManyToMany
    @JoinColumn(name = "users")
    private Set<User> users;

    @OneToOne
    @JoinColumn(name = "moderator")
    private User moderator;








}
