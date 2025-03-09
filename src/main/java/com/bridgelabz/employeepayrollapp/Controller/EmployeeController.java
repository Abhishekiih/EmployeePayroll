package com.bridgelabz.employeepayrollapp.Controller;


import com.bridgelabz.employeepayrollapp.exceptions.EmployeeNotFoundException;
import com.bridgelabz.employeepayrollapp.model.Employee;
import com.bridgelabz.employeepayrollapp.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j  // Enables logging
@RestController
@Validated  // Enables method-level validation
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // GET: Fetch all employees
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // GET: Fetch employee by ID
    @GetMapping("/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    // POST: Add a new employee with validation
    @PostMapping
    public Employee createEmployee(@Valid @RequestBody Employee employee) {
        try {
            return employeeService.createEmployee(employee);
        } catch (Exception e) {
            throw new RuntimeException("Error creating employee: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @Valid @RequestBody Employee updatedEmployee) {
        try {
            return employeeService.updateEmployee(id, updatedEmployee);
        } catch (EmployeeNotFoundException e) {
            throw new EmployeeNotFoundException("Employee not found with ID: " + id);
        } catch (Exception e) {
            throw new RuntimeException("Error updating employee: " + e.getMessage());
        }
    }


    // DELETE: Remove an employee
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        try {
            employeeService.deleteEmployee(id);
            return "Employee deleted successfully";
        } catch (Exception e) {
            throw new RuntimeException("Error deleting employee: " + e.getMessage());
        }

    }
}