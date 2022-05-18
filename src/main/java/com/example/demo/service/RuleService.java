package com.example.demo.service;

import com.example.demo.model.Reaction;
import com.example.demo.model.Rule;

import java.util.List;
import java.util.Optional;

public interface RuleService {

    Optional<Rule> findOne(int id);
    List<Rule> findAll();
    Rule save(Rule rule);
    void delete(int id);

}
