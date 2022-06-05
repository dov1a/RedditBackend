package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
public class FlairCommunity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flair_id", nullable = false)
    private int flairId;

    @Column(name = "name", unique = false, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "community_id")
    private Community communities;

    @Column(name = "active")
    private String active;


    public FlairCommunity(int flairId, String name, Community communities, String active) {
        this.flairId = flairId;
        this.name = name;
        this.communities = communities;
        this.active = active;
    }

    public FlairCommunity() {

    }


}
