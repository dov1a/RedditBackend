package com.example.demo.dto;

import com.example.demo.enums.ReactionType;
import com.example.demo.model.ReactionComment;
import com.example.demo.model.ReactionPost;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ReactionCommentDTO implements Serializable {

    private int reactionId;
    private ReactionType reactionType;
    private LocalDate timestamp;
    private int userId;
    private int commentId;

    public ReactionCommentDTO(ReactionComment reactionComment) {
        this.reactionId = reactionComment.getReactionId();
        this.reactionType = reactionComment.getType();
        this.timestamp = reactionComment.getTimestamp();
        this.userId = reactionComment.getUser().getUserId();
        this.commentId = reactionComment.getComment().getCommentId();
    }
}
