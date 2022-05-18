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
    @Column(name = "community_id", nullable = false)
    private int communityId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "creationDate")
    private LocalDate creationDate;

    @Column(name = "isSuspend")
    private boolean  isSuspend;

    @Column(name = "suspendedReason")
    private String  suspendedReason;

    @ManyToMany
    @JoinColumn(name = "flair_id")
    private Set<Flair> flairs;

    @OneToMany
    @JoinColumn(name = "rule_id")
    private Set<Rule> rules;

    @OneToMany
    @JoinColumn(name = "post_id")
    private Set<Post> posts;

    @OneToMany
    @JoinColumn(name = "banned_id")
    private Set<Banned> banned;

    @ManyToMany
    @JoinColumn(name = "user_id")
    private Set<User> users;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User moderator;









}
