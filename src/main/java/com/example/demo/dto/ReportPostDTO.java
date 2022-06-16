package com.example.demo.dto;

import com.example.demo.enums.ReportReason;
import com.example.demo.model.ReportComment;
import com.example.demo.model.ReportPost;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ReportPostDTO implements Serializable {
    private int reportId;
    private ReportReason reason;
    private LocalDate timestamp;
    private int byUser;
    private int post;
    private boolean accepted;
    private String active;

    public ReportPostDTO(ReportPost reportPost){
        this.reportId = reportPost.getReportId();
        this.reason = reportPost.getReason();
        this.timestamp = reportPost.getTimestamp();
        this.byUser = reportPost.getByUser().getUserId();
        this.post = reportPost.getPost().getPostId();
        this.accepted = reportPost.isAccepted();
        this.active = reportPost.getActive();
    }
}
