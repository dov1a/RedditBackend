package com.example.demo.service;

import com.example.demo.dto.ReactionPostDTO;
import com.example.demo.model.Post;
import com.example.demo.model.ReactionPost;

import java.util.List;
import java.util.Optional;

public interface ReactionPostService {

    Optional<ReactionPost> findOne(int id);
    ReactionPost getOne(int id);
    ReactionPost createReaction(ReactionPostDTO reactionDTO);
    List<ReactionPost> findAll();
    ReactionPost save(ReactionPost reaction);
    void delete(int id);


}
