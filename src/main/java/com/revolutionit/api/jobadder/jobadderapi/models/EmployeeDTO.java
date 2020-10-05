package com.revolutionit.api.jobadder.jobadderapi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDTO {
    private Employee employee;
    private ArrayList<Project> projects;
    private ArrayList<String> currentClients;
    private String projectEndDate;
}
