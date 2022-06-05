package com.example.demo.controller;

import com.example.demo.dto.FlairCommunityDTO;
import com.example.demo.dto.RuleDTO;
import com.example.demo.model.FlairCommunity;
import com.example.demo.model.Rule;
import com.example.demo.service.FlairCommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/communityFlair")
public class FlairController {

    @Autowired
    private FlairCommunityService flairCommunityService;

    @GetMapping("/{communityId}")
    public ResponseEntity<List<FlairCommunityDTO>> getFlairByCommunity(@PathVariable("communityId") Integer id){

        List<FlairCommunity> flairCommunities = flairCommunityService.findAll();
        List<FlairCommunityDTO> flairCommunityDTOS = new ArrayList<>();
        for (FlairCommunity flair : flairCommunities){
            if(flair.getCommunities().getCommunityId() == id && flair.getActive().equals("true")){
                flairCommunityDTOS.add(new FlairCommunityDTO(flair));
            }
        }

        return new ResponseEntity<>(flairCommunityDTOS, HttpStatus.OK);
    };

    @PostMapping("/create")
    public ResponseEntity<FlairCommunityDTO> create(@RequestBody FlairCommunityDTO newFlair){

        FlairCommunity createdFlair = flairCommunityService.createFlair(newFlair);

        if(createdFlair == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        FlairCommunityDTO flairCommunityDTO = new FlairCommunityDTO(createdFlair);

        return new ResponseEntity<>(flairCommunityDTO, HttpStatus.CREATED);
    }

}
