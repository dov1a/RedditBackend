package com.example.demo.service.implementation;

import com.example.demo.dto.FlairCommunityDTO;
import com.example.demo.model.FlairCommunity;
import com.example.demo.repository.FlairCommunityRepository;
import com.example.demo.service.CommunityService;
import com.example.demo.service.FlairCommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlairCommunityServiceImpl implements FlairCommunityService {

    @Autowired
    private FlairCommunityRepository flairRepository;

    @Autowired
    private CommunityService communityService;

    @Override
    public Optional<FlairCommunity> findOne(int id) {
        return flairRepository.findById(id);
    }

    @Override
    public FlairCommunity getOne(int id) {
        return flairRepository.getById(id);
    }

    @Override
    public List<FlairCommunity> findAll() {
        return flairRepository.findAll();
    }

    @Override
    public FlairCommunity createFlair(FlairCommunityDTO flairDTO) {

        Optional<FlairCommunity> flairCommunity = flairRepository.findById(flairDTO.getFlairId());

        if(flairCommunity.isPresent()){

            return null;
        }

        FlairCommunity newFlairCommunity = new FlairCommunity();
        newFlairCommunity.setCommunities(communityService.getOneById(flairDTO.getCommunityId()));
        newFlairCommunity.setName(flairDTO.getName());
        newFlairCommunity.setActive("true");

        newFlairCommunity = flairRepository.save(newFlairCommunity);

        return newFlairCommunity;
    }

    @Override
    public FlairCommunity save(FlairCommunity flair) {
        try{
            return flairRepository.save(flair);
        }catch (IllegalArgumentException e){
            return null;
        }
    }

    @Override
    public void delete(int id) {
        flairRepository.deleteById(id);
    }
}
