package com.example.useremployee.dto;

import com.example.useremployee.model.Employee;
import com.example.useremployee.model.User;
import com.example.useremployee.singleton.Registry;

public class UserEmployeeConverter {
  public UserEmployeeDTO toUserEmployeeDTO( User user, int id ) {

    // GET
    return new UserEmployeeDTO(
      user.getId(),
      user.getEmail(),
      id

    );
  }
}
