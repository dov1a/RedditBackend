package com.example.demo.service;

import com.example.demo.dto.CommentDTO;
import com.example.demo.dto.PostDTO;
import com.example.demo.model.Banned;
import com.example.demo.model.Comment;
import com.example.demo.model.Post;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    Optional<Comment> findOne(int id);
    Comment getOne(int id);
    List<Comment> findAll();
    Comment createComment(CommentDTO commentDTO);
    Comment save(Comment comment);
    void delete(int id);




}
