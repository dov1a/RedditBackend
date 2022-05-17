package com.example.demo.dto;

import com.example.demo.enums.ReactionType;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class ReactionDTO implements Serializable {
    private final int reactionId;
    private final ReactionType type;
    private final LocalDate timestamp;
    private final UserDTO user;
    private final PostDTO post;
    private final CommentDTO comment;
}
