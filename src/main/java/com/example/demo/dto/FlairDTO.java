package com.example.demo.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class FlairDTO implements Serializable {
    private final int flairId;
    private final String name;
    private final int communityId;
    private final int postId;



}
