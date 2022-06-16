package com.example.demo.service;

import com.example.demo.dto.BannedDTO;
import com.example.demo.dto.PostDTO;
import com.example.demo.model.Banned;
import com.example.demo.model.Post;

import java.util.List;
import java.util.Optional;

public interface BannedService {

    Optional<Banned> findOne(int id);
    List<Banned> findAll();
    Banned getOne(int id);
    Banned save(Banned banned);
    Banned createBanned(BannedDTO bannedDTO);
    void delete(int id);

}
