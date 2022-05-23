package com.example.demo.controller;

import com.example.demo.dto.CommunityDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.Community;
import com.example.demo.model.User;
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
        community.setModerator(communityDTO.getModeratorId());


        community = communityService.save(community);

        return new ResponseEntity<>(new CommunityDTO(community), HttpStatus.OK);
    }


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
