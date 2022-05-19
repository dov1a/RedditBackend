package com.example.demo.controller;

import com.example.demo.model.Community;
import com.example.demo.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/communities")
public class CommunityController {

    @Autowired
    private CommunityService communityService;

    @GetMapping
    public ResponseEntity<List<Community>> findAll(){
        return new ResponseEntity<>(communityService.findAll(), HttpStatus.OK);
    };

    @GetMapping("/{id}")
    public ResponseEntity<Community> getOne(@PathVariable("id") Integer id){
        Optional<Community> community = communityService.findOne(id);
        if(!community.isPresent()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(community.get(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Community> create(@RequestBody Community community){

        Community createdCommunity = communityService.save(community);
        if(createdCommunity == null){
            return new ResponseEntity<>(null,HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(createdCommunity, HttpStatus.OK);
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
    public ResponseEntity<Void> deleteCommmunity(@PathVariable Integer id) {

        Optional<Community> community = communityService.findOne(id);

        if (community != null) {
            communityService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
