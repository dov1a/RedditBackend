package com.example.demo.controller;

import com.example.demo.dto.PostDTO;
import com.example.demo.dto.ReactionPostDTO;
import com.example.demo.model.Post;
import com.example.demo.model.ReactionPost;
import com.example.demo.service.PostService;
import com.example.demo.service.ReactionPostService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/reactionPost")
public class ReactionPostController {

    @Autowired
    private ReactionPostService reactionPostService;

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<ReactionPostDTO>> getPostsByCommunity(@PathVariable("postId") Integer id){

        List<ReactionPost> reactionPosts = reactionPostService.findAll();
        List<ReactionPostDTO> reactionPostDTOS = new ArrayList<>();
        for (ReactionPost reaction : reactionPosts){
            if(reaction.getPost().getPostId() == id){
                reactionPostDTOS.add(new ReactionPostDTO(reaction));
            }

        }
        return new ResponseEntity<>(reactionPostDTOS, HttpStatus.OK);
    };

    


    @PostMapping("/create")
    public ResponseEntity<ReactionPostDTO> create(@RequestBody ReactionPostDTO newReaction){

        ReactionPost createdReaction = reactionPostService.createReaction(newReaction);

        if(createdReaction == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        ReactionPostDTO reactionPostDTO = new ReactionPostDTO(createdReaction);

        return new ResponseEntity<>(reactionPostDTO, HttpStatus.CREATED);
    }


}
