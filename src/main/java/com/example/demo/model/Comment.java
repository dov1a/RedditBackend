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
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", nullable = false)
    private int commentId;

    @Column(name = "text", unique = false, nullable = false)
    private String text;

    @Column(name = "timestamp", unique = false, nullable = false)
    private LocalDate timestamp;

//    @ManyToOne
//    @JoinColumn(name = "comment_id")
//    private Comment repliesTo;

    @OneToMany
    @JoinColumn(name = "report_id")
    private Set<ReportPost> reports = new HashSet<>();

    @Column(name = "reaction")
    private int reactions;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(name = "active")
    private String active;



    public Comment(){

    }
}
