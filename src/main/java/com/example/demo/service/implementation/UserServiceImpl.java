package com.example.demo.service.implementation;

import com.example.demo.dto.UserDTO;
import com.example.demo.enums.Roles;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User findOne(String username) {
        Optional<User> user = userRepository.findFirstByUsername(username);
        if (!user.isEmpty()) {
            return user.get();
        }
        return null;
    }

    @Override
    public User findOneById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        }

        return null;
    }


    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User save(User user) {

        try{
            return userRepository.save(user);
        }catch (IllegalArgumentException e){
            return null;
        }
    }

    @Override
    public User createUser(UserDTO userDTO) {
        Optional<User> user = userRepository.findFirstByUsername(userDTO.getUsername());

        if(user.isPresent()){
            return null;
        }

        User newUser = new User();
        newUser.setUsername(userDTO.getUsername());
        newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        newUser.setEmail(userDTO.getEmail());
        newUser.setAvatar(userDTO.getAvatar());
        newUser.setRegistrationDate(LocalDate.now());
        newUser.setDescription(userDTO.getDescription());
        newUser.setDisplayName(userDTO.getDisplayName());
        newUser.setRoles(Roles.USER);
        newUser = userRepository.save(newUser);

        return newUser;
    }

    @Override
    public void delete(int id) {

    }
}
