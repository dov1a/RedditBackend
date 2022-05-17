package com.example.demo.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class BannedDTO implements Serializable {
    private int bannedId;
    private LocalDate timestamp;
    private UserDTO by;
    private CommunityDTO community;
}
