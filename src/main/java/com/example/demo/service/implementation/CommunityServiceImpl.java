package com.example.demo.service.implementation;

import com.example.demo.dto.CommunityDTO;
import com.example.demo.model.Community;
import com.example.demo.model.User;
import com.example.demo.repository.CommunityRepository;
import com.example.demo.service.CommunityService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CommunityServiceImpl implements CommunityService {

    @Autowired
    private CommunityRepository communityRepository;

    @Autowired
    private UserService userService;

    @Override
    public Optional<Community> findOne(int id) {
        return communityRepository.findById(id);
    }

    @Override
    public List<Community> findAll() {
        return communityRepository.findAll();
    }

    @Override
    public Community save(Community community) {
        try{
            return communityRepository.save(community);
        }catch (IllegalArgumentException e){
            return null;
        }
    }

    @Override
    public Community getOneById(int id) {
        return communityRepository.getById(id);
    }

    @Override
    public Community findOneByName(String communityName) {
        Community community = communityRepository.findCommunityByName(communityName);
        if (community != null) {
            return community;
        }
        return null;
    }


    @Override
    public Community createCommunity(CommunityDTO communityDTO) {
        Optional<Community> community = communityRepository.findById(communityDTO.getCommunityId());

        if(community.isPresent()){
            return null;
        }

        Community newCommunity = new Community();
        newCommunity.setName(communityDTO.getName());
        newCommunity.setDescription(communityDTO.getDescription());
        newCommunity.setCreationDate(LocalDate.now());
        newCommunity.setModerator(userService.findOneById(communityDTO.getModerator()));
        newCommunity.setActive("true");

        newCommunity = communityRepository.save(newCommunity);

        return newCommunity;
    }

    @Override
    public void delete(int id) {
        communityRepository.deleteById(id);
    }
}
