package com.example.demo.dto;

import com.example.demo.model.FlairCommunity;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class FlairCommunityDTO implements Serializable {
    private int flairId;
    private String name;
    private int communityId;
    private String active;

    public FlairCommunityDTO(FlairCommunity createFlairCommunity){
        this.flairId = createFlairCommunity.getFlairId();
        this.name = createFlairCommunity.getName();
        this.communityId = createFlairCommunity.getCommunities().getCommunityId();
        this.active = createFlairCommunity.getActive();
    }


}
