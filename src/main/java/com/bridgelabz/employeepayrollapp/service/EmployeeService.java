package com.bridgelabz.employeepayrollapp.service;

import com.bridgelabz.employeepayrollapp.exceptions.EmployeeNotFoundException;
import com.bridgelabz.employeepayrollapp.model.Employee;
import com.bridgelabz.employeepayrollapp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Fetch all employees
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Fetch employee by ID
    public Optional<Employee> getEmployeeById(Long id) {
        return Optional.ofNullable(employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + id)));
    }

    // Add a new employee
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        try {
            return employeeRepository.findById(id).map(employee -> {
                employee.setName(updatedEmployee.getName());
                employee.setDepartment(updatedEmployee.getDepartment());
                employee.setSalary(updatedEmployee.getSalary());
                return employeeRepository.save(employee);
            }).orElseThrow(() -> new EmployeeNotFoundException("Employee not found with ID: " + id));
        } catch (Exception e) {
            throw new RuntimeException("Error updating employee: " + e.getMessage());
        }
    }



    // Delete an employee
    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new RuntimeException("Employee not found with ID: " + id);
        }
        employeeRepository.deleteById(id);
    }
}
