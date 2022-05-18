package com.example.demo.service.implementation;

import com.example.demo.model.Rule;
import com.example.demo.repository.RuleRepository;
import com.example.demo.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RuleServiceImpl implements RuleService {

    @Autowired
    private RuleRepository ruleRepository;

    @Override
    public Optional<Rule> findOne(int id) {
        return ruleRepository.findById(id);
    }

    @Override
    public List<Rule> findAll() {
        return ruleRepository.findAll();
    }

    @Override
    public Rule save(Rule rule) {
        try{
            return ruleRepository.save(rule);
        }catch (IllegalArgumentException e){
            return null;
        }
    }

    @Override
    public void delete(int id) {
        ruleRepository.deleteById(id);
    }
}
