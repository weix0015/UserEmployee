package com.example.useremployee.dto;

import com.example.useremployee.model.Employee;

public record UserDTO(int userID, String email, String password, EmployeeDTO employeeDTO) {
}
