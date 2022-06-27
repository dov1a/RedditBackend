package com.example.demo.controller;

import com.example.demo.dto.BannedDTO;
import com.example.demo.dto.PostDTO;
import com.example.demo.model.Banned;
import com.example.demo.model.Post;
import com.example.demo.service.BannedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "api/banned")
public class BannedController {

    @Autowired
    private BannedService bannedService;

    Logger logger = Logger.getLogger(BannedController.class.getName());

    @GetMapping
    public ResponseEntity<List<BannedDTO>> findAll(){

        List<Banned> banneds = bannedService.findAll();
        List<BannedDTO> bannedDTOS = new ArrayList<>();
        for (Banned banned : banneds){
            bannedDTOS.add(new BannedDTO(banned));
        }

        return new ResponseEntity<>(bannedDTOS, HttpStatus.OK);
    };

    @GetMapping("/{id}")
    public ResponseEntity<BannedDTO> getOne(@PathVariable("id") Integer id){
        Optional<Banned> banned = bannedService.findOne(id);

        BannedDTO bannedDTO = new BannedDTO(banned);

        if(!banned.isPresent()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bannedDTO, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<BannedDTO> create(@RequestBody BannedDTO newBan){

        Banned createdBan = bannedService.createBanned(newBan);

        if(createdBan == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        BannedDTO bannedDTO = new BannedDTO(createdBan);

        logger.info("THE ADMINISTRATOR BANNED THE COMMUNITY!");

        return new ResponseEntity<>(bannedDTO, HttpStatus.CREATED);
    }






}
