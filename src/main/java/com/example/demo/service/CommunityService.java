package com.example.demo.service;


import com.example.demo.dto.CommunityDTO;
import com.example.demo.model.Community;
import com.example.demo.model.User;


import java.util.List;
import java.util.Optional;

public interface CommunityService {

    Optional<Community> findOne(int id);
    List<Community> findAll();
    Community save(Community community);

    Community getOneById(int id);

    Community findOneByName(String communityName);

    Community createCommunity(CommunityDTO communityDTO);

    void delete(int id);

}
