package com.bridgelabz.employeepayrollapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

@Data  // ✅ Includes @Getter, @Setter, @ToString, @EqualsAndHashCode
@NoArgsConstructor  // ✅ Ensures a no-argument constructor for Spring & Jackson
public class EmployeeDTO {

    private String department;

    @NotEmpty(message = "Employee name cannot be empty")
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Employee name Invalid")
    private String name;

    private double salary;

    // ✅ Constructor with Parameters
    public EmployeeDTO(String department, String name, double salary) {
        this.department = department;
        this.name = name;
        this.salary = salary;
    }
}
