package com.example.useremployee.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employee {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String name;
  private LocalDateTime born;
  private Gender gender;
  private boolean vegetarian;

  @OneToOne
  private User user;

  public Employee( String name, LocalDateTime born, Gender gender, boolean vegetarian ) {
    this.name = name;
    this.born = born;
    this.gender = gender;
    this.vegetarian = vegetarian;
  }
}

