package com.example.demo.model;

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
    @Column(name = "rule_id", nullable = false)
    private int ruleId;

    @Column(name = "description", unique = false, nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "community_id", unique = false, nullable = false)
    private Community community;

    @Column(name = "active")
    private String active;


}
