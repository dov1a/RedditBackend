package com.example.demo.service.implementation;

import com.example.demo.dto.ReactionPostDTO;
import com.example.demo.enums.ReactionType;
import com.example.demo.model.Post;
import com.example.demo.model.ReactionPost;
import com.example.demo.repository.ReactionPostRepository;
import com.example.demo.service.PostService;
import com.example.demo.service.ReactionPostService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReactionPostServiceImpl implements ReactionPostService {

    @Autowired
    private ReactionPostRepository reactionPostRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Override
    public Optional<ReactionPost> findOne(int id) {
        return reactionPostRepository.findById(id);
    }

    @Override
    public ReactionPost getOne(int id) {
        return reactionPostRepository.getById(id);
    }

    @Override
    public ReactionPost createReaction(ReactionPostDTO reactionDTO) {

        Optional<ReactionPost> reactionPost = reactionPostRepository.findById(reactionDTO.getReactionId());

        if(reactionPost.isPresent()){

            return null;
        }

        ReactionPost newReactionPost = new ReactionPost();
        newReactionPost.setType(reactionDTO.getReactionType());
        newReactionPost.setTimestamp(LocalDate.now());
        newReactionPost.setUser(userService.findOneById(reactionDTO.getUserId()));
        newReactionPost.setPost(postService.getOne(reactionDTO.getPostId()));

        newReactionPost = reactionPostRepository.save(newReactionPost);

        return newReactionPost;
    }

    @Override
    public List<ReactionPost> findAll() {
        return reactionPostRepository.findAll();
    }

    @Override
    public ReactionPost save(ReactionPost reaction) {
        try{
            return reactionPostRepository.save(reaction);
        }catch (IllegalArgumentException e){
            return null;
        }
    }

    @Override
    public void delete(int id) {
        reactionPostRepository.deleteById(id);
    }


}
