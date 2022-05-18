package com.example.demo.service.implementation;

import com.example.demo.model.Reaction;
import com.example.demo.repository.ReactionRepository;
import com.example.demo.service.ReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReactionServiceImpl implements ReactionService {

    @Autowired
    private ReactionRepository reactionRepository;

    @Override
    public Optional<Reaction> findOne(int id) {
        return reactionRepository.findById(id);
    }

    @Override
    public List<Reaction> findAll() {
        return reactionRepository.findAll();
    }

    @Override
    public Reaction save(Reaction reaction) {
        try{
            return reactionRepository.save(reaction);
        }catch (IllegalArgumentException e){
            return null;
        }
    }

    @Override
    public void delete(int id) {
        reactionRepository.deleteById(id);
    }
}
