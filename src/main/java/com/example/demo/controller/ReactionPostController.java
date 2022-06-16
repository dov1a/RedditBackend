package com.example.demo.controller;

import com.example.demo.dto.KarmaDTO;
import com.example.demo.dto.PostDTO;
import com.example.demo.dto.ReactionPostDTO;
import com.example.demo.enums.ReactionType;
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

    @GetMapping
    public ResponseEntity<List<ReactionPostDTO>> findAll(){

        List<ReactionPost> reactionPosts = reactionPostService.findAll();
        List<ReactionPostDTO> reactionPostDTOS = new ArrayList<>();
        for (ReactionPost reactionPost : reactionPosts){
            reactionPostDTOS.add(new ReactionPostDTO(reactionPost));
        }

        return new ResponseEntity<>(reactionPostDTOS, HttpStatus.OK);
    };

    @GetMapping("/post/{postId}")
    public ResponseEntity<KarmaDTO> getPostKarma(@PathVariable("postId") Integer id){
        KarmaDTO karmaDTO = new KarmaDTO();
        karmaDTO.setKarma(0);
        int upvote = 0;
        int downvote = 0;

        Post onePost = postService.getOne(id);
        List<ReactionPost> reactionPostList = reactionPostService.findAll();
        List<ReactionPost> onePostReaction = new ArrayList<>();



        for (ReactionPost reactionPost : reactionPostList){
            if (reactionPost.getPost().getPostId() == id){
                onePostReaction.add(reactionPost);
            }
        }

        for(ReactionPost reactionPost : onePostReaction){
            if (reactionPost.getType() == ReactionType.UPVOTE){
                upvote = upvote + 1;
            }else if(reactionPost.getType() == ReactionType.DOWNVOTE){
                downvote = downvote + 1;
            }
        }

        karmaDTO.setKarma(upvote - downvote);


        return new ResponseEntity<>(karmaDTO, HttpStatus.OK);
    }

    @GetMapping("/postByUser/{username}")
    public ResponseEntity<KarmaDTO> getPostKarmaByOneUser(@PathVariable("username") String username){
        KarmaDTO karmaDTO = new KarmaDTO();
        karmaDTO.setKarma(0);
        int upvote = 0;
        int downvote = 0;

        List<ReactionPost> reactionPostList = reactionPostService.findAll();
        List<ReactionPost> onePostReaction = new ArrayList<>();



        for (ReactionPost reactionPost : reactionPostList){
            if (reactionPost.getPost().getUser().getUsername().equals(username)){
                onePostReaction.add(reactionPost);
            }
        }

        for(ReactionPost reactionPost : onePostReaction){
            if (reactionPost.getType() == ReactionType.UPVOTE){
                upvote = upvote + 1;
            }else if(reactionPost.getType() == ReactionType.DOWNVOTE){
                downvote = downvote + 1;
            }
        }

        karmaDTO.setKarma(upvote - downvote);


        return new ResponseEntity<>(karmaDTO, HttpStatus.OK);
    }


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
