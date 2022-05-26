package com.example.demo.service;

import com.example.demo.dto.CommentDTO;
import com.example.demo.dto.FlairDTO;
import com.example.demo.model.Comment;
import com.example.demo.model.Community;
import com.example.demo.model.Flair;

import java.util.List;
import java.util.Optional;

public interface FlairService {

    Optional<Flair> findOne(int id);
    Flair getOne(int id);
    List<Flair> findAll();
    Flair createFlair(FlairDTO flairDTO);
    Flair save(Flair flair);
    void delete(int id);


}
