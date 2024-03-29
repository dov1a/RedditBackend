package com.example.demo.controller;

import com.example.demo.dto.PostDTO;
import com.example.demo.dto.RuleDTO;
import com.example.demo.model.Community;
import com.example.demo.model.Post;
import com.example.demo.model.Rule;
import com.example.demo.service.CommunityService;
import com.example.demo.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "api/rules")
public class RuleController {

    @Autowired
    private RuleService ruleService;

    @Autowired
    private CommunityService communityService;

    Logger logger = Logger.getLogger(RuleController.class.getName());

    @GetMapping
    public ResponseEntity<List<RuleDTO>> findAll(){
        List<Rule> rules = ruleService.findAll();
        List<RuleDTO> ruleDTOS = new ArrayList<>();
        for (Rule rule : rules){
            if (rule.getActive().equals("true")){
                ruleDTOS.add(new RuleDTO(rule));
            }
        }

        return new ResponseEntity<>(ruleDTOS, HttpStatus.OK);
    };

    @GetMapping("/community/{communityId}")
    public ResponseEntity<List<RuleDTO>> getRuleByCommunity(@PathVariable("communityId") Integer id){

        List<Rule> rules = ruleService.findAll();
        List<RuleDTO> ruleDTOS = new ArrayList<>();
        for (Rule rule : rules){
            if(rule.getCommunity().getCommunityId() == id && rule.getActive().equals("true")){
                ruleDTOS.add(new RuleDTO(rule));
            }
        }

        return new ResponseEntity<>(ruleDTOS, HttpStatus.OK);
    };

    @GetMapping("/{id}")
    public ResponseEntity<RuleDTO> getOne(@PathVariable("id") Integer id){
        Rule rule = ruleService.getOne(id);

        RuleDTO ruleDTO = new RuleDTO(rule);

        return new ResponseEntity<>(ruleDTO, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<RuleDTO> create(@RequestBody RuleDTO newRule){

        Rule createdRule = ruleService.createRule(newRule);

        if(createdRule == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        RuleDTO ruleDTO = new RuleDTO(createdRule);

        logger.info("THE MODERATOR ADDS A NEW RULE FOR COMMUNITY.");

        return new ResponseEntity<>(ruleDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<RuleDTO> update(@RequestBody RuleDTO ruleDTO, @PathVariable("id") Integer id) {
        Rule rule = ruleService.getOne(id);

        if (rule == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        rule.setDescription(ruleDTO.getDescription());
        rule.setCommunity(communityService.getOneById(rule.getCommunity().getCommunityId()));

        rule = ruleService.save(rule);

        logger.info("THE MODERATOR UPDATED RULE FOR COMMUNITY.");

        return new ResponseEntity<>(new RuleDTO(rule), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<RuleDTO> deleteRule(@PathVariable Integer id) {

        Rule rule = ruleService.getOne(id);

        if (rule == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        rule.setActive("false");

        rule = ruleService.save(rule);

        logger.info("THE MODERATOR DELETED RULE FOR COMMUNITY.");

        return new ResponseEntity<>(new RuleDTO(rule), HttpStatus.OK);
    }

}
