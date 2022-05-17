package com.example.demo.dto;

import com.example.demo.enums.ReportReason;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class ReportDTO implements Serializable {
    private final int reportId;
    private final ReportReason reason;
    private final LocalDate timestamp;
    private final UserDTO byUser;
    private final CommentDTO comment;
    private final PostDTO post;
    private final boolean accepted;
}
