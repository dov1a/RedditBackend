package com.example.demo.repository;

import com.example.demo.model.Community;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Integer> {

    //Optional<Community> findCommunityByName(String communityName);

    Community findCommunityByName(String communityName);

}
