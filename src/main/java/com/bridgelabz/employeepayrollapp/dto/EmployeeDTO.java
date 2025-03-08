package com.bridgelabz.employeepayrollapp.dto;

import lombok.*;


import lombok.Data;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

@Data
public class EmployeeDTO {

    private String department;

    @NotEmpty(message = "Employee name cannot be empty")
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Employee name Invalid")
    private String name;

    private double salary;

    public EmployeeDTO(String department, String name, double salary) {
        this.department = department;
        this.name = name;
        this.salary = salary;
    }
}

