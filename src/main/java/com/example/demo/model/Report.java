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
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id", unique = true, nullable = false)
    private int reportId;

    @Column(name = "reason", unique = false, nullable = false)
    private ReportReason reason;

    @Column(name = "timestamp", unique = false, nullable = false)
    private LocalDate timestamp;

    @OneToOne
    @Column(name = "reason", unique = false, nullable = false)
    private User byUser;

    @ManyToOne
    @Column(name = "comment", unique = false, nullable = false)
    private Comment comment;

    @ManyToOne
    @Column(name = "post", unique = false, nullable = false)
    private Post post;


    @Column(name = "accepted", unique = false, nullable = false)
    private boolean accepted;


}
