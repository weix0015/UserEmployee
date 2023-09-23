package com.example.useremployee.controller;

import com.example.useremployee.dto.EmployeeDTO;
import com.example.useremployee.dto.EmployeeNoIdDTO;
import com.example.useremployee.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.useremployee.singleton.Registry;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
public class EmployeeRestController {

  @GetMapping("/employees")
  public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
    List<Employee> employees = Registry.employeeRepository.findAll();
    List<EmployeeDTO> employeeDTOs = new ArrayList<>();
    employees.forEach( employee -> {
      employeeDTOs.add( Registry.employeeConverter.toDTO( employee ) );
    } );
    return ResponseEntity.ok( employeeDTOs );
  }

  @PostMapping("/employee")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<EmployeeDTO> postEmployee( @RequestBody EmployeeNoIdDTO employeeDTO ) {
    try {
      Employee employee = Registry.employeeConverter.toEntity( employeeDTO );
      Registry.employeeRepository.save( employee );
      return ResponseEntity.ok( Registry.employeeConverter.toDTO( employee ) );
    } catch ( Exception e ) {
      System.err.println( e.getMessage() );
      return ResponseEntity.badRequest().build();
    }
  }

  @PutMapping("/employee/{id}")
  public ResponseEntity<String> putStudent( @PathVariable("id") int id, @RequestBody EmployeeNoIdDTO employeeDTO ) {
    Optional<Employee> optionalEmployee = Registry.employeeRepository.findById( id );
    if ( optionalEmployee.isPresent() ) {
      try {
        Employee employee = Registry.employeeConverter.mergeToEntity( id, employeeDTO );
        Registry.employeeRepository.save( employee );
        return ResponseEntity.ok( "Ok" );
      } catch ( Exception e ) {
        System.err.println( e.getMessage() );
        return ResponseEntity.badRequest().body( e.getMessage() );
      }
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/employee/{id}")
  public ResponseEntity<String> deleteEmployee( @PathVariable("id") int id ) {
    Employee employee = Registry.employeeRepository.findById( id ).orElse( null );
    if ( employee != null ) {
      if ( employee.getUser() != null ) {
        employee.getUser().setEmployee( null );
        Registry.userRepository.save( employee.getUser() );
      }
      Registry.employeeRepository.deleteById( id );
      return ResponseEntity.ok( "Employee Deleted" );
    } else {
      return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( "Employee not found" );
    }
  }

}
