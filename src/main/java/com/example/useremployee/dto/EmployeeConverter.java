package com.example.useremployee.dto;

import com.example.useremployee.model.Employee;
import com.example.useremployee.singleton.Registry;
import org.springframework.stereotype.Component;

@Component
public class EmployeeConverter {

  public Employee toEntity( EmployeeNoIdDTO employeeDTO ) {
    return new Employee(
      employeeDTO.name(),
      employeeDTO.born(),
      employeeDTO.gender(),
      employeeDTO.vegetarian()
    );
  }

  public Employee mergeToEntity( int id, EmployeeNoIdDTO employeeDTO ) {
    Employee employee = Registry.employeeRepository.findById( id ).orElse( null );
    if ( employee == null ) throw new IllegalStateException( "Employee not found" );
    if ( employeeDTO.name() != null ) employee.setName( employeeDTO.name() );
    if ( employeeDTO.born() != null ) employee.setBorn( employeeDTO.born() );
    if ( employeeDTO.gender() != null ) employee.setGender( employeeDTO.gender() );
    if ( employeeDTO.vegetarian() != null ) employee.setVegetarian( employeeDTO.vegetarian() );
    return employee;
  }

  public EmployeeNoIdDTO toNoIdDTO( Employee employee ) {
    return new EmployeeNoIdDTO(
      employee.getName(),
      employee.getBorn(),
      employee.getGender(),
      employee.isVegetarian()
    );
  }

  public EmployeeDTO toDTO( Employee employee ) {
    return new EmployeeDTO(
      employee.getId(),
      employee.getName(),
      employee.getBorn(),
      employee.getGender(),
      employee.isVegetarian()
    );
  }
}
