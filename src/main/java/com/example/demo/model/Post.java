package com.example.demo.model;

import com.example.demo.service.ReactionPostService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

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

    //BACA GRESKU
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "community_id")
    private Community community;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "reaction")
    private int reactions;

    @OneToMany(cascade = {ALL}, fetch = LAZY)
    @JoinColumn(name = "comment_id")
    private Set<Comment> comments;

    @OneToMany(cascade = {ALL}, fetch = LAZY)
    @JoinColumn(name = "report_id")
    private Set<Report> reports;


    @ManyToOne
    @JoinColumn(name = "flair_id")
    private Flair flairs;



    public Post(int postId, String title, String text, LocalDate creationDate, String imagePath, Community community, User user, int reactions, Flair flairs) {
        this.postId = postId;
        this.title = title;
        this.text = text;
        this.creationDate = creationDate;
        this.imagePath = imagePath;
        this.reactions = reactions;
        this.community = community;
        this.user = user;
        this.flairs = flairs;
    }

    public Post(){

    }

}
