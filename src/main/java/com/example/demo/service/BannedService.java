package com.example.demo.service;

import com.example.demo.model.Banned;

import java.util.List;
import java.util.Optional;

public interface BannedService {

    Optional<Banned> findOne(int id);
    List<Banned> findAll();
    Banned save(Banned banned);
    void delete(int id);

}
