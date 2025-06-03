package com.example.employee_matcher.controller;

import com.example.employee_matcher.model.ProjectWorkResult;
import com.example.employee_matcher.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class CSVUploadController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/upload")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<ProjectWorkResult>> uploadCSV(@RequestParam("file") MultipartFile file) {
        List<ProjectWorkResult> result = employeeService.processCSV(file);
        return ResponseEntity.ok(result);
    }
}
