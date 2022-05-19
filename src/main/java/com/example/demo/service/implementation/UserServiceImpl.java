package com.example.demo.service.implementation;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

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

        return userRepository.getById(id);
    }

    @Override
    public User logIn(String username, String password) {
        Optional<User> user = userRepository.findUserByUsernameAndPassword(username, password);
        if (!user.isEmpty()) {
            return user.get();
        }
        return null;
    }


    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public User createUser(UserDTO userDTO) {
        Optional<User> user = userRepository.findFirstByUsername(userDTO.getUsername());

        if(user.isPresent()){
            return null;
        }

        User newUser = new User();
        newUser.setUsername(userDTO.getUsername());
        newUser.setPassword(userDTO.getPassword());
        newUser.setEmail(userDTO.getEmail());
        newUser.setAvatar(userDTO.getAvatar());
        newUser.setRegistrationDate(userDTO.getRegistrationDate());
        newUser.setDescription(userDTO.getDescription());
        newUser.setDisplayName(userDTO.getDisplayName());
        newUser.setUserType(userDTO.getUserType());
        newUser = userRepository.save(newUser);

        return newUser;
    }

    @Override
    public void delete(int id) {

    }
}
