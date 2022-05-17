package com.example.demo.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Data
public class PostDTO implements Serializable {
    private final int postId;
    private final String title;
    private final String text;
    private final LocalDate creationDate;
    private final String imagePath;
    private final CommunityDTO community;
    private final UserDTO user;
    private final Set<ReactionDTO> reactions;
    private final Set<CommentDTO> comments;
    private final Set<ReportDTO> reports;
    private final FlairDTO flairs;
}
