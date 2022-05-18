package com.example.demo.model;

import com.example.demo.enums.ReactionType;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Flair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flair_id", nullable = false)
    private int flairId;

    @Column(name = "name", unique = false, nullable = false)
    private String name;

    @OneToMany
    @JoinColumn(name = "community_id")
    private Set<Community> communities;

    @OneToMany
    @JoinColumn(name = "post_id")
    private Set<Post> posts;


}
