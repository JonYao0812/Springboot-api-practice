package com.practice.api.jobadder.jobadderapi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {
    private EmployeeDetails employeeDetails;
    private List<Project> projects;
    private ArrayList<String> currentClients;
    private LocalDate projectEndDate;
}
