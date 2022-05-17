package com.example.demo.service;

import com.example.demo.model.Banned;
import com.example.demo.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    Optional<Comment> findOne(int id);
    List<Comment> findAll();
    Comment save(Comment comment);
    Comment delete(int id);

}
