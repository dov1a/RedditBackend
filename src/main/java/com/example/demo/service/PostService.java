package com.example.demo.service;

import com.example.demo.model.Flair;
import com.example.demo.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {

    Optional<Post> findOne(int id);
    List<Post> findAll();
    Post save(Post post);
    Post delete(int id);

}
