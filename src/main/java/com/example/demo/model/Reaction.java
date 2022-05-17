package com.example.demo.model;

import com.example.demo.enums.ReactionType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Reaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reaction_id", unique = true, nullable = false)
    private int reactionId;

    @Column(name = "type", unique = false, nullable = false)
    private ReactionType type;

    @Column(name = "timestamp", unique = false, nullable = false)
    private LocalDate timestamp;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "comment")
    private Comment comment;



}
