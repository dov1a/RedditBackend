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
    @Column(name = "comment_id", nullable = false)
    private int commentId;

    @Column(name = "text", unique = false, nullable = false)
    private String text;

    @Column(name = "timestamp", unique = false, nullable = false)
    private LocalDate timestamp;

    @Column(name = "isDeleted", unique = false, nullable = false)
    private boolean isDeleted;

//    @ManyToOne
//    @JoinColumn(name = "comment_id")
//    private Comment repliesTo;

    @OneToMany
    @JoinColumn(name = "report_id")
    private Set<Report> reports = new HashSet<>();

    @OneToMany
    @JoinColumn(name = "reaction_id")
    private Set<ReactionPost> reactions = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;


}
