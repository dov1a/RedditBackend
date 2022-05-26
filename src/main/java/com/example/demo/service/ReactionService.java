package com.example.demo.service;

import com.example.demo.dto.CommentDTO;
import com.example.demo.dto.ReactionDTO;
import com.example.demo.model.Comment;
import com.example.demo.model.Reaction;

import java.util.List;
import java.util.Optional;

public interface ReactionService {

    Optional<Reaction> findOne(int id);
    Reaction getOne(int id);
    Reaction createReaction(ReactionDTO reactionDTO);
    List<Reaction> findAll();
    Reaction save(Reaction reaction);
    void delete(int id);

}
