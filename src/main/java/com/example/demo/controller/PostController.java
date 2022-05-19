package com.example.demo.controller;

import com.example.demo.model.Post;
import com.example.demo.service.PostService;
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
    public ResponseEntity<Post> create(@RequestBody Post post){

        Post createdPost= postService.save(post);
        if(createdPost == null){
            return new ResponseEntity<>(null,HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(createdPost, HttpStatus.OK);
    }

//    @PostMapping
//    public ResponseEntity<User> updateUser(@RequestBody User user){
//
//        User user1 = userService.findOne(user.getUsername());
//
//        if (user == null) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//
//        user1.setUserId(user.getUserId());
//        user1.setUsername(user.getUsername());
//        user1.setPassword(user.getPassword());
//        user1.setEmail(user.getEmail());
//        user1.setAvatar(user.getAvatar());
//        user1.setRegistrationDate(user.getRegistrationDate());
//        user1.setDescription(user.getDescription());
//        user1.setDisplayName(user.getDisplayName());
//        user1.setUserType(user.getUserType());
//
//        user1 = userService.save(user1);
//        return new ResponseEntity<>(new User(user1), HttpStatus.OK);
//    }

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

}
