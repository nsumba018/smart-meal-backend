package com.nsumba.smart_meal.service.impl;

import com.nsumba.smart_meal.model.Employee;
import com.nsumba.smart_meal.repository.EmployeeRepository;
import com.nsumba.smart_meal.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public String saveEmployee(Employee employee) {
        employeeRepository.save(employee);
        return "Employee saved successfully";
    }

    @Override
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> getEmployeeById(UUID id) {
        return employeeRepository.findById(id);
    }

    @Override
    public String updateEmployee(UUID id, Employee employee) {
        if(!employeeRepository.existsById(id)){
            return "The employee with that id does not exist";
        }else{
            employeeRepository.updateEmployeeById(employee.getFirstName(), employee.getLastName(),
                    employee.getStaffNumber(), employee.getEmail(), employee.getDepartment(), id);
            return "Employee updated successfully";
        }
    }

    @Override
    public String deleteEmployee(UUID id) {
        if(!employeeRepository.existsById(id)){
            return "The employee with that id does not exist";
        }else{
            employeeRepository.deleteById(id);
            return "Employee deleted successfully";
        }
    }

    // B1
    @Override
    public List<Employee> getEmployeesByLastName(String lastName) {
        return employeeRepository.findByLastNameIgnoreCaseOrderByFirstNameAsc(lastName);
    }

    // C4
    @Override
    public List<Employee> getEmployeesOfCaterer(UUID catererId) {
        return employeeRepository.findEmployeesOfCaterer(catererId);
    }
}
