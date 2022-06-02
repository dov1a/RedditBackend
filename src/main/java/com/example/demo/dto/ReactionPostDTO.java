package com.example.demo.dto;

import com.example.demo.enums.ReactionType;
import com.example.demo.model.ReactionPost;
import com.example.demo.model.User;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class ReactionPostDTO implements Serializable {
    private int reactionId;
    private ReactionType reactionType;
    private LocalDate timestamp;
    private int userId;
    private int postId;

    public ReactionPostDTO(ReactionPost reactionPost) {
        this.reactionId = reactionPost.getReactionId();
        this.reactionType = reactionPost.getType();
        this.timestamp = reactionPost.getTimestamp();
        this.userId = reactionPost.getUser().getUserId();
        this.postId = reactionPost.getPost().getPostId();

    }

}

