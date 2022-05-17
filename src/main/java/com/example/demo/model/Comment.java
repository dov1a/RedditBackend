package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", unique = true, nullable = false)
    private int commentId;

    @Column(name = "text", unique = false, nullable = false)
    private String text;

    @Column(name = "timestamp", unique = false, nullable = false)
    private LocalDate timestamp;

    @Column(name = "isDeleted", unique = false, nullable = false)
    private boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "repliesTo")
    private Comment repliesTo;

    @OneToMany
    @JoinColumn(name = "reports")
    private Set<Report> reports = new HashSet<>();

    @OneToMany
    @JoinColumn(name = "reactions")
    private Set<Reaction> reactions = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "comment")
    private Comment comment;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post")
    private Post post;


}
