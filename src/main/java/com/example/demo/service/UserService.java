package com.example.demo.service;

import com.example.demo.model.User;

import java.util.List;


public interface UserService {

    User findOne(String username);
    User findOneById(Integer id);
    List<User> findAll();
    User save(User user);
    void delete(int id);

}
