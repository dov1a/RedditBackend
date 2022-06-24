package com.example.demo.service.implementation;

import com.example.demo.dto.ReactionCommentDTO;
import com.example.demo.dto.ReactionPostDTO;
import com.example.demo.model.ReactionComment;
import com.example.demo.model.ReactionPost;
import com.example.demo.repository.ReactionCommentRepository;
import com.example.demo.repository.ReactionPostRepository;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReactionCommentServiceImpl implements ReactionCommentService {


    @Autowired
    private ReactionCommentRepository reactionCommentRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @Override
    public Optional<ReactionComment> findOne(int id) {
        return reactionCommentRepository.findById(id);
    }

    @Override
    public ReactionComment getOne(int id) {
        return reactionCommentRepository.getById(id);
    }

    @Override
    public ReactionComment createReaction(ReactionCommentDTO reactionCommentDTO) {
        Optional<ReactionComment> reactionComment = reactionCommentRepository.findById(reactionCommentDTO.getReactionId());

        if(reactionComment.isPresent()){

            return null;
        }

        ReactionComment newReactionComment = new ReactionComment();
        newReactionComment.setType(reactionCommentDTO.getReactionType());
        newReactionComment.setTimestamp(LocalDate.now());
        newReactionComment.setUser(userService.findOneById(reactionCommentDTO.getUserId()));
        newReactionComment.setComment(commentService.getOne(reactionCommentDTO.getCommentId()));

        newReactionComment = reactionCommentRepository.save(newReactionComment);

        return newReactionComment;
    }

    @Override
    public List<ReactionComment> findAll() {
        return reactionCommentRepository.findAll();
    }

    @Override
    public ReactionComment save(ReactionComment reaction) {
        try{
            return reactionCommentRepository.save(reaction);
        }catch (IllegalArgumentException e){
            return null;
        }
    }

    @Override
    public void delete(int id) {

        reactionCommentRepository.deleteById(id);

    }
}
