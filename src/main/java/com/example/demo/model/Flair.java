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
    @Column(name = "flair_id", unique = true, nullable = false)
    private int flairId;

    @Column(name = "name", unique = false, nullable = false)
    private String name;

    @ManyToMany
    @JoinColumn(name = "communities")
    private Set<Community> communities;

    @OneToMany
    @JoinColumn(name = "post")
    private Set<Post> posts;


}
