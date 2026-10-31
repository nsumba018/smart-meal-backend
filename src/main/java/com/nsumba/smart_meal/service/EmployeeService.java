package com.nsumba.smart_meal.service;

import com.nsumba.smart_meal.model.Employee;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmployeeService {
    String saveEmployee(Employee employee);
    List<Employee> getEmployees();
    Optional<Employee> getEmployeeById(UUID id);
    String updateEmployee(UUID id, Employee employee);
    String deleteEmployee(UUID id);
}
