package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;

import java.util.List;


public interface UserService {

    User findOne(String username);
    User findOneById(Integer id);
    User logIn(String username, String password);
    List<User> findAll();
    User save(User user);

    User createUser(UserDTO userDTO);

    void delete(int id);

}
