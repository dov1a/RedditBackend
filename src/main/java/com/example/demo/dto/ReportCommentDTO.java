package com.example.demo.dto;

import com.example.demo.enums.ReportReason;
import com.example.demo.model.Comment;
import com.example.demo.model.Post;
import com.example.demo.model.ReportComment;
import com.example.demo.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ReportCommentDTO implements Serializable {

    private int reportId;
    private ReportReason reason;
    private LocalDate timestamp;
    private int byUser;
    private int comment;
    private boolean accepted;
    private String active;

    public ReportCommentDTO(ReportComment reportComment){
        this.reportId = reportComment.getReportId();
        this.reason = reportComment.getReason();
        this.timestamp = reportComment.getTimestamp();
        this.byUser = reportComment.getByUser().getUserId();
        this.comment = reportComment.getComment().getCommentId();
        this.accepted = reportComment.isAccepted();
        this.active = reportComment.getActive();
    }



}
