package com.example.demo.controller;

import com.example.demo.dto.FlairCommunityDTO;
import com.example.demo.dto.PostDTO;
import com.example.demo.dto.RuleDTO;
import com.example.demo.model.FlairCommunity;
import com.example.demo.model.Post;
import com.example.demo.model.Rule;
import com.example.demo.model.User;
import com.example.demo.service.FlairCommunityService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "api/communityFlair")
public class FlairController {

    @Autowired
    private FlairCommunityService flairCommunityService;

    Logger logger = Logger.getLogger(FlairController.class.getName());

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

    @GetMapping("/oneFlairById/{flairId}")
    public ResponseEntity<FlairCommunity> getOneByUsername(@PathVariable("flairId") int id){

        Optional<FlairCommunity> flairCommunity = flairCommunityService.findOne(id);

        if(!flairCommunity.isPresent()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(flairCommunity.get(), HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<FlairCommunityDTO> create(@RequestBody FlairCommunityDTO newFlair){

        FlairCommunity createdFlair = flairCommunityService.createFlair(newFlair);

        if(createdFlair == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        FlairCommunityDTO flairCommunityDTO = new FlairCommunityDTO(createdFlair);

        logger.info("ADD NEW FLAIR");

        return new ResponseEntity<>(flairCommunityDTO, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<FlairCommunityDTO> deleteFlair(@PathVariable Integer id) {

        FlairCommunity flairCommunity = flairCommunityService.getOne(id);

        if (flairCommunity == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        flairCommunity.setActive("false");

        flairCommunity = flairCommunityService.save(flairCommunity);

        logger.info("DELETE FLAIR");

        return new ResponseEntity<>(new FlairCommunityDTO(flairCommunity), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<FlairCommunityDTO> updateFlair(@RequestBody FlairCommunityDTO flairCommunityDTO, @PathVariable("id") Integer id) {
        FlairCommunity flairCommunity = flairCommunityService.getOne(id);

        if (flairCommunity == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        flairCommunity.setName(flairCommunityDTO.getName());

        flairCommunity = flairCommunityService.save(flairCommunity);

        return new ResponseEntity<>(new FlairCommunityDTO(flairCommunity), HttpStatus.OK);
    }

}
