package com.example.employee_matcher.service;

import com.example.employee_matcher.model.EmployeeProject;
import com.example.employee_matcher.model.ProjectWorkResult;
import com.example.employee_matcher.util.CSVParserUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class EmployeeService {
    public List<ProjectWorkResult> processCSV(MultipartFile file) {
        List<EmployeeProject> records = CSVParserUtil.parseCSV(file);

        Map<String, Long> totalDaysMap = new HashMap<>();
        List<ProjectWorkResult> detailedResults = new ArrayList<>();

        Map<Integer, List<EmployeeProject>> projectsMap = new HashMap<>();
        for (EmployeeProject record : records) {
            projectsMap.computeIfAbsent(record.getProjectId(), k -> new ArrayList<>()).add(record);
        }

        for (Map.Entry<Integer, List<EmployeeProject>> entry : projectsMap.entrySet()) {
            int projectId = entry.getKey();
            List<EmployeeProject> empList = entry.getValue();

            for (int i = 0; i < empList.size(); i++) {
                for (int j = i + 1; j < empList.size(); j++) {
                    EmployeeProject e1 = empList.get(i);
                    EmployeeProject e2 = empList.get(j);

                    LocalDate overlapStart = e1.getDateFrom().isAfter(e2.getDateFrom()) ? e1.getDateFrom() : e2.getDateFrom();
                    LocalDate overlapEnd = e1.getDateTo().isBefore(e2.getDateTo()) ? e1.getDateTo() : e2.getDateTo();

                    if (!overlapStart.isAfter(overlapEnd)) {
                        long daysWorked = ChronoUnit.DAYS.between(overlapStart, overlapEnd) + 1;

                        if (e1.getEmpId() == e2.getEmpId()) continue;
                        int empId1 = Math.min(e1.getEmpId(), e2.getEmpId());
                        int empId2 = Math.max(e1.getEmpId(), e2.getEmpId());
                        String pairKey = empId1 + "-" + empId2;

                        totalDaysMap.put(pairKey, totalDaysMap.getOrDefault(pairKey, 0L) + daysWorked);
                        detailedResults.add(new ProjectWorkResult(empId1, empId2, projectId, daysWorked));
                    }
                }
            }
        }

        String maxPair = totalDaysMap.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);

        if (maxPair == null) return List.of();

        int maxEmp1 = Integer.parseInt(maxPair.split("-")[0]);
        int maxEmp2 = Integer.parseInt(maxPair.split("-")[1]);

        List<ProjectWorkResult> resultForMaxPair = new ArrayList<>();
        for (ProjectWorkResult res : detailedResults) {
            if ((res.getEmpId1() == maxEmp1 && res.getEmpId2() == maxEmp2)) {
                resultForMaxPair.add(res);
            }
        }

        return resultForMaxPair;
    }
}
