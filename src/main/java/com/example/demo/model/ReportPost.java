package com.example.demo.model;

import com.example.demo.enums.ReportReason;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReportPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id", nullable = false)
    private int reportId;

    @Column(name = "reason")
    private ReportReason reason;

    @Column(name = "timestamp")
    private LocalDate timestamp;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User byUser;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(name = "accepted")
    private boolean accepted;

    @Column(name = "active")
    private String active;


}
