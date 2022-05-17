package com.example.demo.service;

import com.example.demo.model.Banned;
import com.example.demo.model.Community;

import java.util.List;
import java.util.Optional;

public interface CommunityService {

    Optional<Community> findOne(int id);
    List<Community> findAll();
    Community save(Community community);
    Community delete(int id);

}
