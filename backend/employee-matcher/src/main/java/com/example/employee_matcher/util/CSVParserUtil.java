package com.example.employee_matcher.util;

import com.example.employee_matcher.model.EmployeeProject;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CSVParserUtil {
    private static final List<DateTimeFormatter> SUPPORTED_FORMATS = List.of(
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("dd.MM.yyyy"),
            DateTimeFormatter.ofPattern("MM/dd/yyyy")
    );

    public static List<EmployeeProject> parseCSV(MultipartFile file) {
        List<EmployeeProject> projects = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",\\s*");
                if (tokens.length < 4 || tokens[0].equalsIgnoreCase("EmpID")) continue;

                int empId = Integer.parseInt(tokens[0]);
                int projectId = Integer.parseInt(tokens[1]);
                LocalDate from = parseDate(tokens[2]);
                LocalDate to = tokens[3].equalsIgnoreCase("NULL") ? LocalDate.now() : parseDate(tokens[3]);

                projects.add(new EmployeeProject(empId, projectId, from, to));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return projects;
    }

    private static LocalDate parseDate(String dateStr) {
        for (DateTimeFormatter formatter : SUPPORTED_FORMATS) {
            try {
                return LocalDate.parse(dateStr.trim(), formatter);
            } catch (Exception ignored) {}
        }
        throw new IllegalArgumentException("Unsupported date format: " + dateStr);
    }
}
