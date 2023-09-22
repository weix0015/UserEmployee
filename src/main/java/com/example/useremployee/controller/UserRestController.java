package com.example.useremployee.controller;

import com.example.useremployee.dto.UserConverter;
import com.example.useremployee.dto.UserDTO;
import com.example.useremployee.model.User;
import com.example.useremployee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserRestController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserConverter userConverter;

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<User> userList = userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        userList.forEach(user -> {
            userDTOList.add(userConverter.toDTO(user));
        });
        return ResponseEntity.ok(userDTOList);
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO postUser(@RequestBody UserDTO userDTO){
        User user = userConverter.toEntity(userDTO);
        user.setUserID(0);
        System.out.println(user);
        return userConverter.toDTO(user);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<UserDTO> putUser(@PathVariable("id") int id, @RequestBody UserDTO userDTO){
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = userConverter.toEntity(userDTO);
            user.setUserID(id);
            userRepository.save(user);
            return ResponseEntity.ok(userConverter.toDTO(user));
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int id){
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()){
            userRepository.deleteById(id);
            return ResponseEntity.ok("User deleted");
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
