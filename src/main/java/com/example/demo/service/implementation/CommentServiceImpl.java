package com.example.demo.service.implementation;

import com.example.demo.dto.CommentDTO;
import com.example.demo.model.Comment;
import com.example.demo.repository.CommentRepository;
import com.example.demo.service.CommentService;
import com.example.demo.service.PostService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Override
    public Optional<Comment> findOne(int id) {
        return commentRepository.findById(id);
    }

    @Override
    public Comment getOne(int id) {
        return commentRepository.getById(id);
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment createComment(CommentDTO commentDTO) {

        Optional<Comment> comment = commentRepository.findById(commentDTO.getCommentId());

        if(comment.isPresent()){

            return null;
        }

        Comment newComment = new Comment();
        newComment.setText(commentDTO.getText());
        newComment.setTimestamp(LocalDate.now());
        newComment.setUser(userService.findOneById(commentDTO.getUser()));
        newComment.setPost(postService.getOne(commentDTO.getCommentId()));
        newComment.setActive("true");

        newComment = commentRepository.save(newComment);

        return newComment;

    }

    @Override
    public Comment save(Comment comment) {
        try{
            return commentRepository.save(comment);
        }catch (IllegalArgumentException e){
            return null;
        }
    }

    @Override
    public void delete(int id) {
        commentRepository.deleteById(id);
    }
}
