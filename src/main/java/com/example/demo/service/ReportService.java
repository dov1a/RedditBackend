package com.example.demo.service;

import com.example.demo.model.Post;
import com.example.demo.model.Reaction;

import java.util.List;
import java.util.Optional;

public interface ReportService {

    Optional<Reaction> findOne(int id);
    List<Reaction> findAll();
    Reaction save(Reaction reaction);
    Reaction delete(int id);

}
