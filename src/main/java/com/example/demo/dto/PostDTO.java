package com.example.demo.dto;

import com.example.demo.model.*;
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
    private int community;
    private int user;
    private int reactions;
    private Set<Comment> comments;
    private Set<Report> reports;
    private FlairCommunity flairs;

    public PostDTO(Post createdPost) {
        this.postId = createdPost.getPostId();
        this.title = createdPost.getTitle();
        this.text = createdPost.getText();
        this.creationDate = createdPost.getCreationDate();
        this.imagePath = createdPost.getImagePath();
        this.community = createdPost.getCommunity().getCommunityId();
        this.user = createdPost.getUser().getUserId();
        this.flairs = createdPost.getFlairs();
    }

}
