package com.example.demo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RuleDTO implements Serializable {
    private final int ruleId;
    private final String description;
    private final CommunityDTO community;
}
