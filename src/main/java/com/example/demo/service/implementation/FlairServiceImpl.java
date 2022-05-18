package com.example.demo.service.implementation;

import com.example.demo.model.Flair;
import com.example.demo.repository.FlairRepository;
import com.example.demo.service.FlairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlairServiceImpl implements FlairService {

    @Autowired
    private FlairRepository flairRepository;

    @Override
    public Optional<Flair> findOne(int id) {
        return flairRepository.findById(id);
    }

    @Override
    public List<Flair> findAll() {
        return flairRepository.findAll();
    }

    @Override
    public Flair save(Flair flair) {
        try{
            return flairRepository.save(flair);
        }catch (IllegalArgumentException e){
            return null;
        }
    }

    @Override
    public void delete(int id) {
        flairRepository.deleteById(id);
    }
}
