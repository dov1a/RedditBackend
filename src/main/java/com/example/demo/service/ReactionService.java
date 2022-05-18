package com.example.demo.service;

import com.example.demo.model.Reaction;

import java.util.List;
import java.util.Optional;

public interface ReactionService {

    Optional<Reaction> findOne(int id);
    List<Reaction> findAll();
    Reaction save(Reaction reaction);
    void delete(int id);
}
