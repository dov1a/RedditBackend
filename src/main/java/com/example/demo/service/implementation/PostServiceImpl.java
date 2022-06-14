package com.example.demo.service.implementation;

import com.example.demo.dto.PostDTO;
import com.example.demo.model.*;
import com.example.demo.repository.PostRepository;
import com.example.demo.service.CommunityService;
import com.example.demo.service.FlairCommunityService;
import com.example.demo.service.PostService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CommunityService communityService;

    @Autowired
    private FlairCommunityService flairCommunityService;

    @Override
    public Optional<Post> findOne(Integer id) {
        return postRepository.findById(id);
    }

    @Override
    public Post getOne(Integer id) {
        return postRepository.getById(id);
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Post save(Post post) {
        try{
            return postRepository.save(post);
        }catch (IllegalArgumentException e){
            return null;
        }
    }

    @Override
    public Post createPost(PostDTO postDTO) {
        Optional<Post> post = postRepository.findById(postDTO.getPostId());

        if(post.isPresent()){

            return null;
        }


        Post newPost = new Post();
        newPost.setTitle(postDTO.getTitle());
        newPost.setText(postDTO.getText());
        newPost.setCreationDate(LocalDate.now());
        newPost.setImagePath(postDTO.getImagePath());
        newPost.setCommunity(communityService.getOneById(postDTO.getCommunity()));
        newPost.setUser(userService.findOneById(postDTO.getUser()));
        newPost.setActive("true");
        newPost.setFlairs(flairCommunityService.getOne(postDTO.getFlair()));

        newPost = postRepository.save(newPost);

        return newPost;
    }

    @Override
    public void delete(int id) {
        postRepository.deleteById(id);
    }
}
