package com.example.demo.service.implementation;

import com.example.demo.model.Community;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.CommunityRepository;
import com.example.demo.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommunityServiceImpl implements CommunityService {

    @Autowired
    private CommunityRepository communityRepository;

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
    public void delete(int id) {
        communityRepository.deleteById(id);
    }
}
