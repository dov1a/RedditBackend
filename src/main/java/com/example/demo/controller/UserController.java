package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.enums.Roles;
import com.example.demo.model.Post;
import com.example.demo.model.User;
import com.example.demo.security.TokenUtils;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "api/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    TokenUtils tokenUtils;

    Logger logger = Logger.getLogger(UserController.class.getName());

    @GetMapping
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

    @GetMapping("/byId/{id}")
    public ResponseEntity<User> getOneById(@PathVariable("id") int id){
        User user = userService.findOneById(id);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/onlyUser")
    public ResponseEntity<List<User>> findAllUser(){

        List<User> allUsers = userService.findAll();
        List<User> user = new ArrayList<>();
        for (User user1 : allUsers){
            if (user1.getRoles() == Roles.USER){
                user.add(user1);
            }
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    };

    @GetMapping("/onlyBlockUser")
    public ResponseEntity<List<User>> findBlockUser(){

        List<User> allUsers = userService.findAll();
        List<User> user = new ArrayList<>();
        for (User user1 : allUsers){
            if (user1.getRoles() == Roles.BLOCK_USER){
                user.add(user1);
            }
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    };

    @GetMapping("/onlyModerators")
    public ResponseEntity<List<User>> findAllModerators(){

        List<User> allUsers = userService.findAll();
        List<User> user = new ArrayList<>();
        for (User user1 : allUsers){
            if (user1.getRoles() == Roles.MODERATOR){
                user.add(user1);
            }
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    };


    @PostMapping("/create")
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO newUser){

        User createdUser = userService.createUser(newUser);

        if(createdUser == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        UserDTO userDTO = new UserDTO(createdUser);

        logger.info("USER HAS REGISTERED");

        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{username}", consumes = "application/json")
    public ResponseEntity<Void> changePassword(@RequestBody @Validated ResetPasswordDTO resetPasswordDTO, @PathVariable("username") String username){
        User user = userService.findOne(username);

        if (passwordEncoder.matches(resetPasswordDTO.getOldPassword(), user.getPassword())){
            user.setPassword(passwordEncoder.encode(resetPasswordDTO.getNewPassword()));
            user = userService.save(user);

            logger.info("USER HAS CHANGE HIS PASSWORD");

            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

    }


    @DeleteMapping(value = "/blockUser/{id}")
    public ResponseEntity<UserDTO> blockUser(@PathVariable Integer id) {

        User user = userService.findOneById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        user.setRoles(Roles.BLOCK_USER);

        user = userService.save(user);

        logger.info("MODERATOR BLOCKED THE USER");

        return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
    }

    @DeleteMapping(value = "/changeUserToModerator/{id}")
    public ResponseEntity<UserDTO> changeUserToModerator(@PathVariable Integer id) {

        User user = userService.findOneById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        user.setRoles(Roles.MODERATOR);

        user = userService.save(user);

        return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
    }

    @DeleteMapping(value = "/changeModeratorToUser/{id}")
    public ResponseEntity<UserDTO> changeModeratorToUser(@PathVariable Integer id) {

        User user = userService.findOneById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        user.setRoles(Roles.USER);

        user = userService.save(user);

        return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
    }

    @DeleteMapping(value = "/unBlockUser/{id}")
    public ResponseEntity<UserDTO> unBlockUser(@PathVariable Integer id) {

        User user = userService.findOneById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        user.setRoles(Roles.USER);

        user = userService.save(user);

        return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
    }


    @PostMapping("/login")
    public ResponseEntity<UserTokenState> createAuthenticationToken(
            @RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletResponse response) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(), authenticationRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails user = (UserDetails) authentication.getPrincipal();
        String jwt = tokenUtils.generateToken(user);
        int expiresIn = tokenUtils.getExpiredIn();

        logger.info("USER HAS LOGGED IN");

        return ResponseEntity.ok(new UserTokenState(jwt, expiresIn));
    }

    @PutMapping(value = "/changeData/{username}", consumes = "application/json")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO, @PathVariable("username") String username) {
        User user = userService.findOne(username);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        user.setEmail(userDTO.getEmail());
        user.setDisplayName(userDTO.getDisplayName());
        user.setDescription(userDTO.getDescription());
        user.setAvatar(userDTO.getAvatar());

        user = userService.save(user);

        logger.info("USER HAS CHANGE HIS DATA ");

        return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
    }




}
