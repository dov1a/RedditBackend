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
    @Column(name = "post_id", unique = true, nullable = false)
    private int postId;

    @Column(name = "title", unique = false, nullable = false)
    private String title;

    @Column(name = "text", unique = false, nullable = false)
    private String text;

    @Column(name = "creationDate", unique = false, nullable = false)
    private LocalDate creationDate;

    @Column(name = "imagePath", unique = false, nullable = false)
    private String imagePath;

    @ManyToOne
    @JoinColumn(name = "community", nullable = false)
    private Community community;

    @ManyToOne
    @JoinColumn(name = "user", nullable = false)
    private User user;

    @OneToMany(cascade = {ALL}, fetch = LAZY)
    @JoinColumn(name = "reactions")
    private Set<Reaction> reactions;

    @OneToMany(cascade = {ALL}, fetch = LAZY)
    @JoinColumn(name = "comments")
    private Set<Comment> comments;

    @OneToMany(cascade = {ALL}, fetch = LAZY)
    @JoinColumn(name = "reports")
    private Set<Report> reports;

    @ManyToOne
    @JoinColumn(name = "flairs")
    private Flair flairs;







}
