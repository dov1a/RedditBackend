package com.example.demo.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Data
public class CommunityDTO implements Serializable {
    private final int communityId;
    private final String name;
    private final String description;
    private final LocalDate creationDate;
    private final boolean isSuspend;
    private final String suspendedReason;
    private final Set<FlairDTO> flairs;
    private final Set<RuleDTO> rules;
    private final Set<PostDTO> posts;
    private final Set<BannedDTO> banned;
    private final Set<UserDTO> users;
    private final UserDTO moderator;
}
