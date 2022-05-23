package com.example.demo.dto;

import com.example.demo.model.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PostDTO implements Serializable {
    private int postId;
    private String title;
    private String text;
    private LocalDate creationDate;
    private String imagePath;
    private Community community;
    private User user;
    private Set<Reaction> reactions;
    private Set<Comment> comments;
    private Set<Report> reports;
    private Flair flairs;

    public PostDTO(Post createdPost) {

        this.postId = createdPost.getPostId();
        this.title = createdPost.getTitle();
        this.text = createdPost.getText();
        this.creationDate = createdPost.getCreationDate();
        this.imagePath = createdPost.getImagePath();
        this.community = createdPost.getCommunity();
        this.user = createdPost.getUser();
        this.flairs = createdPost.getFlairs();

    }



}
