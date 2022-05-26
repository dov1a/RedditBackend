package com.example.demo.controller;

import com.example.demo.dto.CommunityDTO;
import com.example.demo.dto.PostDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.Community;
import com.example.demo.model.Post;
import com.example.demo.model.User;
import com.example.demo.service.CommunityService;
import com.example.demo.service.PostService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(value = "api/communities")
public class CommunityController {

    @Autowired
    private CommunityService communityService;

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;



    @GetMapping
    public ResponseEntity<List<CommunityDTO>> findAll(){

        List<Community> communities = communityService.findAll();
        List<CommunityDTO> communityDTOS = new ArrayList<>();
        for (Community community : communities){
            communityDTOS.add(new CommunityDTO(community));
        }

        return new ResponseEntity<>(communityDTOS, HttpStatus.OK);
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
    public ResponseEntity<CommunityDTO> create(@RequestBody CommunityDTO newCommunity){

        Community createdCommunity = communityService.createCommunity(newCommunity);

        if(createdCommunity == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        CommunityDTO communityDTO = new CommunityDTO(createdCommunity);

        return new ResponseEntity<>(communityDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<CommunityDTO> updatePost(@RequestBody CommunityDTO communityDTO, @PathVariable("id") Integer id) {
        Community community = communityService.getOneById(id);

        if (community == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        community.setName(communityDTO.getName());
        community.setDescription(communityDTO.getDescription());
        community.setCreationDate(communityDTO.getCreationDate());
        community.isSuspend(communityDTO.isSuspend());
        community.setSuspendedReason(communityDTO.getSuspendedReason());
        community.setPosts(communityDTO.getPosts());
        community.setFlairs(communityDTO.getFlairs());
        community.setModerator(userService.findOneById(community.getModerator().getUserId()));


        community = communityService.save(community);

        return new ResponseEntity<>(new CommunityDTO(community), HttpStatus.OK);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteCommmunity(@PathVariable Integer id) {


        Optional<Community> community = communityService.findOne(id);

        Set<Post> communityPosts = community.get().getPosts();



        if (community != null) {

//            for (Post post : communityPosts)
//            {
//
//                PostController postController = new PostController();
//                postController.deletePost(post.getPostId());
//            }

            communityService.delete(community.get().getCommunityId());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
