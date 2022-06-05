package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "community_id", nullable = false)
    private int communityId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "creationDate")
    private LocalDate creationDate;

    @Column(name = "isSuspend")
    private boolean  isSuspend;

    @Column(name = "suspendedReason")
    private String  suspendedReason;


    @ManyToMany(cascade = {CascadeType.ALL}, fetch = LAZY)
    @JoinColumn(name = "flair_id")
    private Set<FlairCommunity> flairs;

    //BACA GRESKU
    @JsonIgnore
    @OneToMany(cascade = {CascadeType.ALL}, fetch = LAZY)
    @JoinColumn(name = "rule_id")
    private Set<Rule> rules;


    @OneToMany(cascade = {CascadeType.ALL}, fetch = LAZY)
    @JoinColumn(name = "post_id")
    private Set<Post> posts;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "banned_id")
    private Set<Banned> banned;

    @JsonIgnore
    @ManyToMany
    @JoinColumn(name = "user_id")
    private Set<User> users;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User moderator;


    public Community(int communityId, String name, String description, LocalDate creationDate, boolean isSuspend, String suspendedReason, User moderator) {
        this.communityId = communityId;
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
        this.isSuspend = isSuspend;
        this.suspendedReason = suspendedReason;
        this.moderator = moderator;
    }

    public Community(){

    }

    public int getCommunityId() {
        return communityId;
    }

    public void setCommunityId(int communityId) {
        this.communityId = communityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isSuspend(boolean suspend) {
        return isSuspend;
    }

    public void setSuspend(boolean suspend) {
        isSuspend = suspend;
    }

    public String getSuspendedReason() {
        return suspendedReason;
    }

    public void setSuspendedReason(String suspendedReason) {
        this.suspendedReason = suspendedReason;
    }

    public User getModerator() {
        return moderator;
    }

    public void setModerator(User moderator) {
        this.moderator = moderator;
    }
}
