package com.example.demo.service.implementation;

import com.example.demo.dto.RuleDTO;
import com.example.demo.model.Post;
import com.example.demo.model.Rule;
import com.example.demo.repository.RuleRepository;
import com.example.demo.service.CommunityService;
import com.example.demo.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RuleServiceImpl implements RuleService {

    @Autowired
    private RuleRepository ruleRepository;

    @Autowired
    private CommunityService communityService;

    @Override
    public Optional<Rule> findOne(int id) {
        return ruleRepository.findById(id);
    }

    @Override
    public List<Rule> findAll() {
        return ruleRepository.findAll();
    }

    @Override
    public Rule getOne(int id) {
        return ruleRepository.getById(id);
    }

    @Override
    public Rule createRule(RuleDTO ruleDTO) {
        Optional<Rule> rule = ruleRepository.findById(ruleDTO.getRuleId());

        if(rule.isPresent()){
            return null;
        }

        Rule newRule = new Rule();
        newRule.setCommunity(communityService.getOneById(rule.get().getCommunity().getCommunityId()));
        newRule.setDescription(ruleDTO.getDescription());
        newRule.setActive("true");

        newRule = ruleRepository.save(newRule);

        return newRule;
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
