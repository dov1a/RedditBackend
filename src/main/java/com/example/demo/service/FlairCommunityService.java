package com.example.demo.service;

import com.example.demo.dto.FlairCommunityDTO;
import com.example.demo.model.FlairCommunity;

import java.util.List;
import java.util.Optional;

public interface FlairCommunityService {

    Optional<FlairCommunity> findOne(int id);
    FlairCommunity getOne(int id);
    List<FlairCommunity> findAll();
    FlairCommunity createFlair(FlairCommunityDTO flairDTO);
    FlairCommunity save(FlairCommunity flair);
    void delete(int id);


}
