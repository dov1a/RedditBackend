package com.example.demo.dto;

import com.example.demo.enums.ReactionType;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class ReactionCommentDTO implements Serializable {

    private int reactionId;
    private ReactionType type;
    private LocalDate timestamp;
    private int userId;
    private int commentId;
}
