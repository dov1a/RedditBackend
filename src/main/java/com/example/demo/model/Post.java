package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", nullable = false)
    private int postId;

    @Column(name = "title")
    private String title;

    @Column(name = "text")
    private String text;

    @Column(name = "creationDate")
    private LocalDate creationDate;

    @Column(name = "imagePath")
    private String imagePath;

    @ManyToOne
    @JoinColumn(name = "community_id")
    private Community community;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = {ALL}, fetch = LAZY)
    @JoinColumn(name = "reaction_id")
    private Set<Reaction> reactions;

    @OneToMany(cascade = {ALL}, fetch = LAZY)
    @JoinColumn(name = "comment_id")
    private Set<Comment> comments;

    @OneToMany(cascade = {ALL}, fetch = LAZY)
    @JoinColumn(name = "report_id")
    private Set<Report> reports;

    @ManyToOne
    @JoinColumn(name = "flair_id")
    private Flair flairs;








}
