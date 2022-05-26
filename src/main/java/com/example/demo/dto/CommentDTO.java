package com.example.demo.dto;

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
public class CommentDTO implements Serializable {
    private int commentId;
    private String text;
    private LocalDate timestamp;
    private boolean isDeleted;
    //private CommentDTO repliesTo;
    private Set<ReportDTO> reports;
    private Set<ReactionDTO> reactions;
    //private CommentDTO comment;
    private UserDTO user;
    private PostDTO post;
}
