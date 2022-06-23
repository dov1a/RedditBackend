package com.example.demo.controller;

import com.example.demo.dto.CommunityDTO;
import com.example.demo.dto.PostDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.enums.Roles;
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
            if (community.getActive().equals("true")){
                communityDTOS.add(new CommunityDTO(community));
            }

        }

        return new ResponseEntity<>(communityDTOS, HttpStatus.OK);
    };

    @GetMapping("/{id}")
    public ResponseEntity<CommunityDTO> getOne(@PathVariable("id") Integer id){
        Community community = communityService.getOneById(id);

        if (community.getActive().equals("false")){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        CommunityDTO communityDTO = new CommunityDTO(community);

        return new ResponseEntity<>(communityDTO, HttpStatus.OK);
    }

    @GetMapping("/byCommunityName/{communityName}")
    public ResponseEntity<CommunityDTO> getOneByCommunityName(@PathVariable("communityName") String communityName){

        Community community = communityService.findOneByName(communityName);
        if(community == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        CommunityDTO communityDTO = new CommunityDTO(community);

        return new ResponseEntity<>(communityDTO, HttpStatus.OK);
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
    public ResponseEntity<CommunityDTO> updateCommunity(@RequestBody CommunityDTO communityDTO, @PathVariable("id") Integer id) {
        Community community = communityService.getOneById(id);

        if (community == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        community.setName(communityDTO.getName());
        community.setDescription(communityDTO.getDescription());
        community.setCreationDate(communityDTO.getCreationDate());
        community.setModerator(userService.findOneById(community.getModerator().getUserId()));


        community = communityService.save(community);

        return new ResponseEntity<>(new CommunityDTO(community), HttpStatus.OK);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<CommunityDTO> deleteCommmunity(@PathVariable Integer id) {

        //TREBA DA OBRISEM COMMUNITY
        //OBJAVE COMMUNITY
        //AKO POSTOJI JEDAN MODERATOR OD COMMUNITY ON POSTAJE OBICAN KORISNIK



        //KOMENTARE OBJAVA
        //REAKCIJE KOMENTARA
        //REAKCIJE OBJAVA

        List<Post> allPosts = postService.findAll();

        List<Post> communityPosts = new ArrayList<>();



        Community community = communityService.getOneById(id);

        if (community == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        community.setActive("false");

        community = communityService.save(community);

        for (Post post: allPosts){
            if(post.getCommunity().getCommunityId() == id){
                communityPosts.add(post);
            }
        }

        for (Post post: communityPosts ){
            post.setActive("false");
            postService.save(post);
        }

        User communityModerator = userService.findOneById(community.getModerator().getUserId());

        communityModerator.setRoles(Roles.USER);

        userService.save(communityModerator);

        return new ResponseEntity<>(new CommunityDTO(community), HttpStatus.OK);
    }

}
