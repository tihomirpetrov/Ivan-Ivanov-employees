package com.example.employee_matcher.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeProject {
    private int empId;
    private int projectId;
    private LocalDate dateFrom;
    private LocalDate dateTo;
}
