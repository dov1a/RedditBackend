package com.example.demo.controller;

import com.example.demo.dto.PostDTO;
import com.example.demo.model.*;
import com.example.demo.service.CommunityService;
import com.example.demo.service.PostService;
import com.example.demo.service.ReactionPostService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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


    @GetMapping
    public ResponseEntity<List<PostDTO>> findAll(){



        List<Post> posts = postService.findAll();
        List<PostDTO> postDTOS = new ArrayList<>();
        for (Post post : posts){
            int karma = reactionPostService.karma(post.getPostId());

            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("OVO JE KARMA OD OBJAVE " + post.getPostId() + "I VREDNOST JE " + karma  );
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            postDTOS.add(new PostDTO(post));
        }

        return new ResponseEntity<>(postDTOS, HttpStatus.OK);
    };

    @GetMapping("/community/{communityId}")
    public ResponseEntity<List<PostDTO>> getPostsByCommunity(@PathVariable("communityId") Integer id){

        List<Post> posts = postService.findAll();
        List<PostDTO> postDTOS = new ArrayList<>();
        for (Post post : posts){
            if(post.getCommunity().getCommunityId() == id){
                postDTOS.add(new PostDTO(post));




            }

        }
        return new ResponseEntity<>(postDTOS, HttpStatus.OK);
    };


    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getOne(@PathVariable("id") Integer id){
        Post posts = postService.getOne(id);

        PostDTO postDTO = new PostDTO(posts);

//        if(po){
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        }
        return new ResponseEntity<>(postDTO, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<PostDTO> create(@RequestBody PostDTO newPost){

        Post createdPost = postService.createPost(newPost);

        if(createdPost == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        PostDTO postDTO = new PostDTO(createdPost);

        return new ResponseEntity<>(postDTO, HttpStatus.CREATED);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Integer id) {

        Optional<Post> post = postService.findOne(id);

        if (post != null) {
            postService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO, @PathVariable("id") Integer id) {
        Post post = postService.getOne(id);

        if (post == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        post.setTitle(postDTO.getTitle());
        post.setText(postDTO.getText());
        post.setCreationDate(postDTO.getCreationDate());
        post.setImagePath(postDTO.getImagePath());
        post.setCommunity(communityService.getOneById(post.getCommunity().getCommunityId()));
        post.setUser(userService.findOneById(post.getUser().getUserId()));
        post.setReactions(postDTO.getReactions());
        post.setComments(postDTO.getComments());
        post.setReports(postDTO.getReports());
        post.setFlairs(postDTO.getFlairs());


        post = postService.save(post);

        return new ResponseEntity<>(new PostDTO(post), HttpStatus.OK);
    }

}
