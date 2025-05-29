package com.example.employee_matcher.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectWorkResult {
    @JsonProperty("empId1")
    private int empId1;
    @JsonProperty("empId2")
    private int empId2;
    @JsonProperty("projectId")
    private int projectId;
    @JsonProperty("daysWorked")
    private long daysWorked;
}
