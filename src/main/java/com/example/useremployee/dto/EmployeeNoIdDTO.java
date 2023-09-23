package com.example.useremployee.dto;

import com.example.useremployee.model.Gender;

import java.time.LocalDateTime;

public record EmployeeNoIdDTO( String name, LocalDateTime born, Gender gender, Boolean vegetarian ) {
}
