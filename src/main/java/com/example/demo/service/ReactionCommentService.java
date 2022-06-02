package com.example.demo.service;

import com.example.demo.dto.ReactionCommentDTO;
import com.example.demo.dto.ReactionPostDTO;
import com.example.demo.model.ReactionComment;
import com.example.demo.model.ReactionPost;

import java.util.List;
import java.util.Optional;

public interface ReactionCommentService {

    Optional<ReactionComment> findOne(int id);
    ReactionComment getOne(int id);
    ReactionComment createReaction(ReactionCommentDTO reactionCommentDTO);
    List<ReactionComment> findAll();
    ReactionComment save(ReactionComment reaction);
    void delete(int id);

}
