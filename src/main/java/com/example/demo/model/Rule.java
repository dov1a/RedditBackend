package com.example.demo.model;

import com.example.demo.enums.ReportReason;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Rule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rule_id", unique = true, nullable = false)
    private int ruleId;

    @Column(name = "description", unique = false, nullable = false)
    private String description;

    @ManyToOne
    @Column(name = "community", unique = false, nullable = false)
    private Community community;
}
