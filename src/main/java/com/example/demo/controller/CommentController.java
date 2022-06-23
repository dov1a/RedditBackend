package com.example.demo.controller;

import com.example.demo.dto.CommentDTO;
import com.example.demo.dto.PostDTO;
import com.example.demo.dto.ReactionCommentDTO;
import com.example.demo.dto.ReactionPostDTO;
import com.example.demo.enums.ReactionType;
import com.example.demo.model.Comment;
import com.example.demo.model.Post;
import com.example.demo.model.ReactionComment;
import com.example.demo.model.ReactionPost;
import com.example.demo.service.CommentService;
import com.example.demo.service.ReactionCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ReactionCommentService reactionCommentService;

    @GetMapping
    public ResponseEntity<List<CommentDTO>> findAll(){

        List<Comment> comments = commentService.findAll();
        List<CommentDTO> commentDTOS = new ArrayList<>();
        for (Comment comment : comments){
            if (comment.getActive().equals("true")){
                commentDTOS.add(new CommentDTO(comment));
            }

        }

        return new ResponseEntity<>(commentDTOS, HttpStatus.OK);
    };

    @GetMapping("/getOneById/{id}")
    public ResponseEntity<CommentDTO> getOne(@PathVariable("id") Integer id){
        Comment comment = commentService.getOne(id);

        if (comment.getActive().equals("false")){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        CommentDTO commentDTO = new CommentDTO(comment);

        return new ResponseEntity<>(commentDTO, HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<List<CommentDTO>> getCommentsByPost(@PathVariable("postId") Integer id){

        List<Comment> comments = commentService.findAll();

        List<CommentDTO> commentDTOS = new ArrayList<>();
        for (Comment comment : comments){
            if(comment.getPost().getPostId() == id && comment.getActive().equals("true")){
                commentDTOS.add(new CommentDTO(comment));
            }
        }
        return new ResponseEntity<>(commentDTOS, HttpStatus.OK);
    };

    @PostMapping("/create")
    public ResponseEntity<CommentDTO> create(@RequestBody CommentDTO newComment){

        Comment createdComment = commentService.createComment(newComment);

        if(createdComment == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        CommentDTO commentDTO = new CommentDTO(createdComment);

        //CREATE REACTION FOR COMMENT
        ReactionCommentDTO reactionComment = new ReactionCommentDTO();
        reactionComment.setCommentId(commentDTO.getCommentId());
        reactionComment.setReactionType(ReactionType.UPVOTE);
        reactionComment.setUserId(reactionComment.getUserId());
        ReactionComment createdReaction = reactionCommentService.createReaction(reactionComment);


        return new ResponseEntity<>(commentDTO, HttpStatus.CREATED);
    }

}
