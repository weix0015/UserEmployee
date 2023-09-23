package com.example.useremployee.controller;

import com.example.useremployee.dto.UserNoIdDTO;
import com.example.useremployee.dto.UserNoIdNoPasswordNoEmployeeDTO;
import com.example.useremployee.dto.UserNoPasswordDTO;
import com.example.useremployee.model.Employee;
import com.example.useremployee.model.User;
import com.example.useremployee.singleton.Registry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserRestController {
  @GetMapping("/users")
  public ResponseEntity<List<UserNoPasswordDTO>> getAllUsers() {
    List<User> users = Registry.userRepository.findAll();
    List<UserNoPasswordDTO> userNoPasswordDTOs = new ArrayList<>();
    users.forEach( user -> {
      userNoPasswordDTOs.add( Registry.userConverter.toNoPasswordDTO( user ) );
    } );
    return ResponseEntity.ok( userNoPasswordDTOs );
  }

  @PostMapping("/user")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<UserNoPasswordDTO> postUser( @RequestBody UserNoIdDTO userDTO ) {
    try {
      User user = Registry.userConverter.mergeToEntity( userDTO );
      Registry.userRepository.save( user );
      user.getEmployee().setUser( user );
      Registry.employeeRepository.save( user.getEmployee() );
      return ResponseEntity.ok( Registry.userConverter.toNoPasswordDTO( user ) );
    } catch ( Exception e ) {
      System.err.println( e.getMessage() );
      return ResponseEntity.badRequest().build();
    }
  }

  @PutMapping("/user/{id}")
  public ResponseEntity<String> putUser( @PathVariable("id") int id, @RequestBody UserNoIdNoPasswordNoEmployeeDTO userDTO ) {
    Optional<User> optionalUser = Registry.userRepository.findById( id );
    if ( optionalUser.isPresent() ) {
      try {
        User user = Registry.userConverter.mergeToEntity( id, userDTO );
        Registry.userRepository.save( user );
        return ResponseEntity.ok("Ok");
      } catch ( Exception e ) {
        System.err.println( e.getMessage() );
        return ResponseEntity.badRequest().body( e.getMessage() );
      }
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/user/{id}")
  public ResponseEntity<String> deleteUser( @PathVariable("id") int id ) {
    User user = Registry.userRepository.findById( id ).orElse( null );
    if ( user != null ) {
      Employee employee = user.getEmployee();
      if ( employee != null ) {
        user.setEmployee( null );
        employee.setUser( null );
        Registry.employeeRepository.save( employee );
      }
      Registry.userRepository.deleteById( id );
      return ResponseEntity.ok( "User deleted" );
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
