package com.example.demo.controller;

import com.example.demo.dto.PostDTO;
import com.example.demo.dto.ReactionPostDTO;
import com.example.demo.enums.ReactionType;
import com.example.demo.model.*;
import com.example.demo.service.CommunityService;
import com.example.demo.service.PostService;
import com.example.demo.service.ReactionPostService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommunityService communityService;

    @Autowired
    private ReactionPostService reactionPostService;

    Logger logger = Logger.getLogger(PostController.class.getName());

    @GetMapping
    public ResponseEntity<List<PostDTO>> findAll(){

        List<Post> posts = postService.findAll();
        List<PostDTO> postDTOS = new ArrayList<>();
        for (Post post : posts){
            if (post.getActive().equals("true")){
                postDTOS.add(new PostDTO(post));
            }

        }

        return new ResponseEntity<>(postDTOS, HttpStatus.OK);
    };


    @GetMapping("/community/{communityId}")
    public ResponseEntity<List<PostDTO>> getPostsByCommunity(@PathVariable("communityId") Integer id){

        List<Post> posts = postService.findAll();
        List<PostDTO> postDTOS = new ArrayList<>();
        for (Post post : posts){
            if(post.getCommunity().getCommunityId() == id && post.getActive().equals("true")){
                postDTOS.add(new PostDTO(post));

            }

        }
        return new ResponseEntity<>(postDTOS, HttpStatus.OK);
    };

    @GetMapping("/user/{username}")
    public ResponseEntity<List<PostDTO>> getPostsByUser(@PathVariable("username") String username){

        List<Post> posts = postService.findAll();

        List<PostDTO> postDTOS = new ArrayList<>();
        for (Post post : posts){
            if(post.getUser().getUsername().equals(username) && post.getActive().equals("true")){
                postDTOS.add(new PostDTO(post));

            }

        }
        return new ResponseEntity<>(postDTOS, HttpStatus.OK);
    };


    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getOne(@PathVariable("id") Integer id){
        Post posts = postService.getOne(id);

        if (posts.getActive().equals("false")){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        PostDTO postDTO = new PostDTO(posts);

        return new ResponseEntity<>(postDTO, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<PostDTO> create(@RequestBody PostDTO newPost){

        Post createdPost = postService.createPost(newPost);


        if(createdPost == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        PostDTO postDTO = new PostDTO(createdPost);

        //CREATE REACTION FOR POST
        ReactionPostDTO reactionPost = new ReactionPostDTO();
        reactionPost.setPostId(postDTO.getPostId());
        reactionPost.setReactionType(ReactionType.UPVOTE);
        reactionPost.setUserId(postDTO.getUser());
        ReactionPost createdReaction = reactionPostService.createReaction(reactionPost);

        logger.info("USER HAS CREATED A NEW POST");

        return new ResponseEntity<>(postDTO, HttpStatus.CREATED);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<PostDTO> deletePost(@PathVariable Integer id) {

        Post post = postService.getOne(id);

        if (post == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        post.setActive("false");

        post = postService.save(post);

        logger.info("USER HAS DELETE HIS POST");

        return new ResponseEntity<>(new PostDTO(post), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO, @PathVariable("id") Integer id) {
        Post post = postService.getOne(id);

        if (post == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        post.setTitle(postDTO.getTitle());
        post.setText(postDTO.getText());
        post.setCommunity(communityService.getOneById(post.getCommunity().getCommunityId()));
        post.setUser(userService.findOneById(post.getUser().getUserId()));

        post = postService.save(post);

        logger.info("USER EDITED HIS POST");

        return new ResponseEntity<>(new PostDTO(post), HttpStatus.OK);
    }

}
