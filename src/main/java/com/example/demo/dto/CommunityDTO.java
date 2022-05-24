package com.example.demo.dto;

import com.example.demo.model.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CommunityDTO implements Serializable {
    private int communityId;
    private String name;
    private String description;
    private LocalDate creationDate;
    private boolean isSuspend;
    private String suspendedReason;
    private Set<Flair> flairs;
    private Set<Rule> rules;
    private Set<Post> posts;
    private Set<Banned> banned;
    private Set<User> users;
    private User moderator;

    public CommunityDTO(Community createdCommunity) {
        this.communityId = createdCommunity.getCommunityId();
        this.name = createdCommunity.getName();
        this.description = createdCommunity.getDescription();
        this.creationDate = createdCommunity.getCreationDate();
        this.isSuspend = createdCommunity.isSuspend();
        this.suspendedReason = createdCommunity.getSuspendedReason();
        this.moderator = createdCommunity.getModerator();
    }


}
