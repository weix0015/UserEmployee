package com.example.useremployee.dto;

import com.example.useremployee.model.Gender;

import java.time.LocalDateTime;

public record EmployeeDTO( int id, String name, LocalDateTime born, Gender gender, Boolean vegetarian ) {
}
