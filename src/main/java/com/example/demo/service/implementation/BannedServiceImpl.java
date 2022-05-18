package com.example.demo.service.implementation;

import com.example.demo.model.Banned;
import com.example.demo.repository.BannedRepository;
import com.example.demo.service.BannedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BannedServiceImpl implements BannedService {

    @Autowired
    private BannedRepository bannedRepository;

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
    public void delete(int id) {
        bannedRepository.deleteById(id);
    }
}
