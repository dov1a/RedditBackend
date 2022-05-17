package com.example.demo.service;

import com.example.demo.model.Rule;
import com.example.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findOne(String username);
    List<User> findAll();
    User save(User user);
    User delete(int id);

}
