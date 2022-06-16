package com.example.demo.dto;

import com.example.demo.model.Banned;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
public class BannedDTO implements Serializable {
    private int bannedId;
    private LocalDate timestamp;
    private int byUser;
    private int community;
    private String bannedReason;

    public BannedDTO(Banned createBanned){
        this.bannedId = createBanned.getBannedId();
        this.timestamp = createBanned.getTimestamp();
        this.byUser = createBanned.getBy().getUserId();
        this.community = createBanned.getCommunity().getCommunityId();
        this.bannedReason = createBanned.getBannedReason();

    }

    public BannedDTO(Optional<Banned> banned) {
        this.bannedId = banned.get().getBannedId();
        this.timestamp = banned.get().getTimestamp();
        this.byUser = banned.get().getBy().getUserId();
        this.community = banned.get().getCommunity().getCommunityId();
        this.bannedReason = banned.get().getBannedReason();
    }
}
