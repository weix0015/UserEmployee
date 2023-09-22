package com.example.useremployee.dto;

import com.example.useremployee.model.Gender;
import com.example.useremployee.model.User;

import java.time.LocalDateTime;

public record EmployeeDTO(int id, String name, LocalDateTime born, Gender gender, boolean vegetarian, User user) {
}
