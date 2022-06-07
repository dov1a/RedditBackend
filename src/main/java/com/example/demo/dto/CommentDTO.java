package com.example.demo.dto;

import com.example.demo.model.Comment;
import com.example.demo.model.Post;
import com.example.demo.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CommentDTO implements Serializable {
    private int commentId;
    private String text;
    private LocalDate timestamp;
    //private CommentDTO repliesTo;
    private Set<ReportDTO> reports;
    private int reactions;
    //private CommentDTO comment;
    private int user;
    private int post;

    public CommentDTO(Comment createComment){
        this.commentId = createComment.getCommentId();
        this.text = createComment.getText();
        this.timestamp = createComment.getTimestamp();
        this.user = createComment.getUser().getUserId();
        this.post = createComment.getPost().getPostId();
    }
}
