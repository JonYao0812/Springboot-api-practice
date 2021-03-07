package com.practice.api.jobadder.jobadderapi.models;

import lombok.*;
import javax.persistence.*;
import java.util.HashMap;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDetails {

    @Id
    private String id;
    private String name;
    private int location;
    private int practice;
    private int empType;
    private HashMap probation;
    private int subsidiary;

}
