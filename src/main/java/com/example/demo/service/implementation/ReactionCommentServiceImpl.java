package com.example.demo.service.implementation;

import com.example.demo.dto.ReactionCommentDTO;
import com.example.demo.dto.ReactionPostDTO;
import com.example.demo.model.ReactionComment;
import com.example.demo.model.ReactionPost;
import com.example.demo.repository.ReactionPostRepository;
import com.example.demo.service.PostService;
import com.example.demo.service.ReactionCommentService;
import com.example.demo.service.ReactionPostService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReactionCommentServiceImpl implements ReactionCommentService {


    @Override
    public Optional<ReactionComment> findOne(int id) {
        return Optional.empty();
    }

    @Override
    public ReactionComment getOne(int id) {
        return null;
    }

    @Override
    public ReactionComment createReaction(ReactionCommentDTO reactionCommentDTO) {
        return null;
    }

    @Override
    public List<ReactionComment> findAll() {
        return null;
    }

    @Override
    public ReactionComment save(ReactionComment reaction) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
