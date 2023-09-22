package com.example.useremployee.repository;

import com.example.useremployee.model.Employee;
import org.hibernate.JDBCException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeRepositoryTest {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    void testAtLeastOneAnna(){
        List<Employee> lst = employeeRepository.findEmployeeByName("Anna");
        assertTrue(lst.size()>0);
    }

    @Test
    void testDeleteEmployee(){
        List<Employee> lst = employeeRepository.findEmployeeByName("Anna");
        Employee emp1 = lst.get(0);
        //userRepository.delete(emp1.getUser()); //does not work in unit testing
        assertThrows(DataIntegrityViolationException.class, () -> userRepository.delete(emp1.getUser()));
    }
}