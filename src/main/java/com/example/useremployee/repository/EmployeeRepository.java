package com.example.useremployee.repository;

import com.example.useremployee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
  List<Employee> findEmployeeByName(String name);
}
