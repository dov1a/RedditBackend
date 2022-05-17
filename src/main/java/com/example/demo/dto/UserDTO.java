package com.example.demo.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Data
public class UserDTO implements Serializable {
    private final int userId;
    private final String username;
    private final String password;
    private final String email;
    private final String avatar;
    private final LocalDate registrationDate;
    private final String description;
    private final String displayName;
    private final String userType;
    private final Set<CommunityDTO> communities;
    private final Set<PostDTO> posts;
    private final Set<CommentDTO> comments;
    private final Set<ReactionDTO> reactions;
    private final Set<BannedDTO> banneds;
    private final CommunityDTO moderatesCommunity;
}
