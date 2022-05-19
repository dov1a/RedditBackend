package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> findAll(){
      return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    };

    @GetMapping("/{username}")
    public ResponseEntity<User> getOneByUsername(@PathVariable("username") String username){
        Optional<User> users = Optional.ofNullable(userService.findOne(username));
        if(!users.isPresent()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(users.get(), HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO newUser){

        User createdUser = userService.createUser(newUser);

        if(createdUser == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        UserDTO userDTO = new UserDTO(createdUser);

        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

//    @PostMapping
//    public ResponseEntity<User> updateUser(@RequestBody User user){
//
//        User user1 = userService.findOne(user.getUsername());
//
//        if (user == null) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//
//        user1.setUserId(user.getUserId());
//        user1.setUsername(user.getUsername());
//        user1.setPassword(user.getPassword());
//        user1.setEmail(user.getEmail());
//        user1.setAvatar(user.getAvatar());
//        user1.setRegistrationDate(user.getRegistrationDate());
//        user1.setDescription(user.getDescription());
//        user1.setDisplayName(user.getDisplayName());
//        user1.setUserType(user.getUserType());
//
//        user1 = userService.save(user1);
//        return new ResponseEntity<>(new User(user1), HttpStatus.OK);
//    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {

        User user = userService.findOneById(id);

        if (user != null) {
            userService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
