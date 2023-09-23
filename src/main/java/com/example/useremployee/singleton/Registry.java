package com.example.useremployee.singleton;

import com.example.useremployee.dto.EmployeeConverter;
import com.example.useremployee.dto.UserConverter;
import com.example.useremployee.dto.UserEmployeeConverter;
import com.example.useremployee.repository.EmployeeRepository;
import com.example.useremployee.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Registry {
  @Autowired
  public UserRepository _userRepository;
  @Autowired
  public EmployeeRepository _employeeRepository;
  public static UserRepository userRepository;
  public static EmployeeRepository employeeRepository;
  public static UserConverter userConverter = new UserConverter();
  public static EmployeeConverter employeeConverter = new EmployeeConverter();

  public static UserEmployeeConverter userEmployeeConverter = new UserEmployeeConverter();

  @PostConstruct
  private void init() {
    userRepository = this._userRepository;
    employeeRepository = this._employeeRepository;
  }
}
