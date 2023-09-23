package com.example.useremployee.dto;

import com.example.useremployee.model.Employee;
import com.example.useremployee.model.User;
import com.example.useremployee.singleton.Registry;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

  public UserNoPasswordDTO toNoPasswordDTO( User user ) {
    // GET
    Employee employee = user.getEmployee();
    return new UserNoPasswordDTO(
      user.getId(),
      user.getEmail(),
      employee != null ? Registry.employeeConverter.toDTO( employee ) : null
    );
  }

  public User mergeToEntity( int id, UserNoIdNoPasswordNoEmployeeDTO userDTO ) {
    // PUT
    User oldUser = Registry.userRepository.findById( id ).orElse( null );
    if ( oldUser == null ) throw new IllegalStateException( "User not found" );

    oldUser.setEmail( userDTO.email() );
    return oldUser;
  }

  public User mergeToEntity( UserNoIdDTO userDTO ) {
    // POST
    if ( Registry.userRepository.findByEmail( userDTO.email() ) != null ) throw new IllegalStateException( "Email already exists" );

    EmployeeDTO employeeDTO = userDTO.employeeDTO();
    User newUser = new User( userDTO.email(), userDTO.password() );
    if ( employeeDTO != null ) {
      Employee employee = Registry.employeeRepository.findById( userDTO.employeeDTO().id() ).orElse( null );
      if ( employee == null ) throw new IllegalStateException( "Employee not found" );
      if ( employee.getUser() != null ) throw new IllegalStateException( "Employee already has a user" );
      newUser.setEmployee( employee );
    }
    return newUser;
  }
}
