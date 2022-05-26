package com.example.demo.dto;

import com.example.demo.model.Rule;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class RuleDTO implements Serializable {
    private int ruleId;
    private String description;
    private int community;

    public RuleDTO(Rule createRule){
        this.ruleId = createRule.getRuleId();
        this.description = createRule.getDescription();
        this.community = createRule.getCommunity().getCommunityId();
    }

}
