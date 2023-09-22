package com.example.useremployee.dto;

import com.example.useremployee.model.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeConverter {

    public Employee toEntity(EmployeeDTO employeeDTO){
        return new Employee(
                employeeDTO.id(),
                employeeDTO.name(),
                employeeDTO.born(),
                employeeDTO.gender(),
                employeeDTO.vegetarian(),
                employeeDTO.user()
        );
    }

    public EmployeeDTO toDTO(Employee employee){
        return new EmployeeDTO(
                employee.getId(),
                employee.getName(),
                employee.getBorn(),
                employee.getGender(),
                employee.isVegetarian(),
                employee.getUser()
        );
    }
}
