package com.example.demo.service.implementation;

import com.example.demo.dto.BannedDTO;
import com.example.demo.dto.CommunityDTO;
import com.example.demo.model.Banned;
import com.example.demo.model.Community;
import com.example.demo.repository.BannedRepository;
import com.example.demo.service.BannedService;
import com.example.demo.service.CommunityService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BannedServiceImpl implements BannedService {

    @Autowired
    private BannedRepository bannedRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CommunityService communityService;

    @Override
    public Optional<Banned> findOne(int id) {
        return bannedRepository.findById(id);
    }

    @Override
    public List<Banned> findAll() {
        return bannedRepository.findAll();
    }

    @Override
    public Banned save(Banned banned) {
        try{
            return bannedRepository.save(banned);
        }catch (IllegalArgumentException e){
            return null;
        }
    }

    @Override
    public Banned createBanned(BannedDTO bannedDTO) {
        Optional<Banned> banned = bannedRepository.findById(bannedDTO.getBannedId());

        if(banned.isPresent()){
            return null;
        }

        Banned newBan = new Banned();
        newBan.setTimestamp((bannedDTO.getTimestamp()));
        newBan.setBy(userService.findOneById(banned.get().getBy().getUserId()));
        newBan.setCommunity(communityService.getOneById(banned.get().getCommunity().getCommunityId()));

        newBan = bannedRepository.save(newBan);

        return newBan;
    }

    @Override
    public void delete(int id) {
        bannedRepository.deleteById(id);
    }
}
