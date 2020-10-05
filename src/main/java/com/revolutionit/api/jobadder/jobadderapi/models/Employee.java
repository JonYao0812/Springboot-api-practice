package com.revolutionit.api.jobadder.jobadderapi.models;


import lombok.*;
import org.hibernate.annotations.SQLInsert;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {

    @Id
    private String id;
    private String name;
    private String location;
    private String practice;
    private String empType;
    private HashMap<String,String> probation;
    private HashMap<String,String> probationDate;
    private String subsidiary;


////    @Singular
////    private List<String> currentClients;
//
//    @OneToMany
//    @JoinColumn(name="raId")
//    @Singular
//    private List<Project> projects;
}
