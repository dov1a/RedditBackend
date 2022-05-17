package com.example.demo.service;

import com.example.demo.model.Community;
import com.example.demo.model.Flair;

import java.util.List;
import java.util.Optional;

public interface FlairService {

    Optional<Flair> findOne(int id);
    List<Flair> findAll();
    Flair save(Flair flair);
    Flair delete(int id);

}
