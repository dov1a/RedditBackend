package com.example.demo.service;

import com.example.demo.dto.PostDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.Flair;
import com.example.demo.model.Post;
import com.example.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface PostService {

    Optional<Post> findOne(Integer id);
    Post getOne(Integer id);
    List<Post> findAll();
    Post save(Post post);
    Post createPost(PostDTO postDTO);
    void delete(int id);

}
