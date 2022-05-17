package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Banned {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "banned_id", unique = true, nullable = false)
    private int bannedId;

    @Column(name = "timestamp", unique = false, nullable = false)
    private LocalDate timestamp;

    @ManyToOne
    @JoinColumn(name = "by", unique = false, nullable = false)
    private User by;

    @ManyToOne
    @JoinColumn(name = "community", unique = false, nullable = false)
    private Community community;


}
