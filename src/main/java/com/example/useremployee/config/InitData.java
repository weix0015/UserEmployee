package com.example.useremployee.config;

import com.example.useremployee.model.Employee;
import com.example.useremployee.model.Gender;
import com.example.useremployee.model.User;
import com.example.useremployee.repository.EmployeeRepository;
import com.example.useremployee.repository.UserRepository;
import com.example.useremployee.singleton.Registry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class InitData implements CommandLineRunner {
  // GET /user -> user (no pwd), employee
  // POST /user -> user, employee id
  // PUT /user -> user (no pwd)
  // GET /employee -> employee, user (no pwd)
  // POST /employee -> employee
  // PUT /employee -> employee

  // DTOs required: user with employee, user no pwd with employee, user no pwd, employee

  @Override
  public void run( String... args ) throws Exception {
    Registry.userRepository.save( new User( "bjarne@aol.com", "1234" ) );
    Registry.userRepository.save( new User( "ib@google.com", "password" ) );

    Employee emp1 = new Employee();
    emp1.setName( "Anna" );
    emp1.setGender( Gender.FEMALE );
    emp1.setVegetarian( false );
    emp1.setBorn( LocalDateTime.of( 2000, 1, 1, 7, 55, 0 ) );
    User user1 = new User( "anna@aol.com", "abcd" );
    Registry.userRepository.save( user1 );
    emp1.setUser( user1 );
    Registry.employeeRepository.save( emp1 );
  }
}
