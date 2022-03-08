package com.example.springsoapwebservicedemo.service;

import com.example.springsoapwebservicedemo.model.Employee;

public interface EmployeeService {
    void addEmployee(Employee employee);
    Employee getEmployeeById(long employeeId);
    void updateEmployee(Employee employee);
    void deleteEmployee(long employeeId);
}
