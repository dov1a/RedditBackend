package com.example.demo.controller;

import com.example.demo.dto.KarmaDTO;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/reactionComment")
public class ReactionCommentController {

    @Autowired
    private ReactionCommentService reactionCommentService;

    @Autowired
    private CommentService commentService;

    @GetMapping
    public ResponseEntity<List<ReactionCommentDTO>> findAll(){

        List<ReactionComment> reactionComments = reactionCommentService.findAll();
        List<ReactionCommentDTO> reactionCommentDTOS = new ArrayList<>();
        for (ReactionComment reactionComment : reactionComments){
            reactionCommentDTOS.add(new ReactionCommentDTO(reactionComment));
        }

        return new ResponseEntity<>(reactionCommentDTOS, HttpStatus.OK);
    };

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/create")
    public ResponseEntity<ReactionCommentDTO> create(@RequestBody ReactionCommentDTO newReaction){

        ReactionComment createdReaction = reactionCommentService.createReaction(newReaction);

        if(createdReaction == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        ReactionCommentDTO reactionCommentDTO = new ReactionCommentDTO(createdReaction);

        return new ResponseEntity<>(reactionCommentDTO, HttpStatus.CREATED);
    }

    @GetMapping("/comment/{commentId}")
    public ResponseEntity<KarmaDTO> getPostKarma(@PathVariable("commentId") Integer id){
        KarmaDTO karmaDTO = new KarmaDTO();
        karmaDTO.setKarma(0);
        int upvote = 0;
        int downvote = 0;

        Comment oneComment = commentService.getOne(id);
        List<ReactionComment> reactionCommentList = reactionCommentService.findAll();
        List<ReactionComment> oneCommentReaction = new ArrayList<>();



        for (ReactionComment reactionComment : reactionCommentList){
            if (reactionComment.getComment().getCommentId() == id){
                oneCommentReaction.add(reactionComment);
            }
        }

        for(ReactionComment reactionComment : oneCommentReaction){
            if (reactionComment.getType() == ReactionType.UPVOTE){
                upvote = upvote + 1;
            }else if(reactionComment.getType() == ReactionType.DOWNVOTE){
                downvote = downvote + 1;
            }
        }

        karmaDTO.setKarma(upvote - downvote);


        return new ResponseEntity<>(karmaDTO, HttpStatus.OK);
    }

}
