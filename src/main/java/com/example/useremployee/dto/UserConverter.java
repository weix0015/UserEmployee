package com.example.useremployee.dto;

import com.example.useremployee.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    @Autowired
    EmployeeConverter employeeConverter;

    public User toEntity(UserDTO userDTO) {
        return new User(
                userDTO.userID(),
                userDTO.email(),
                userDTO.password(),
                employeeConverter.toEntity(userDTO.employeeDTO())
        );
    }

    public UserDTO toDTO(User user) {
        return new UserDTO(
                user.getUserID(),
                user.getEmail(),
                user.getPassword(),
                employeeConverter.toDTO(user.getEmployee())
        );
    }
}
