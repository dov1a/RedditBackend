package com.example.demo.controller;

import com.example.demo.dto.PostDTO;
import com.example.demo.model.Post;
import com.example.demo.service.PostService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<Post>> findAll(){
        return new ResponseEntity<>(postService.findAll(), HttpStatus.OK);
    };


    @GetMapping("/{id}")
    public ResponseEntity<Post> getOne(@PathVariable("id") Integer id){
        Optional<Post> posts = postService.findOne(id);

        if(!posts.isPresent()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(posts.get(), HttpStatus.OK);
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
        post.setCommunity(postDTO.getCommunity());
        post.setUser(postDTO.getUser());
        post.setReactions(postDTO.getReactions());
        post.setComments(postDTO.getComments());
        post.setReports(postDTO.getReports());
        post.setFlairs(postDTO.getFlairs());


        post = postService.save(post);

        return new ResponseEntity<>(new PostDTO(post), HttpStatus.OK);
    }

}
