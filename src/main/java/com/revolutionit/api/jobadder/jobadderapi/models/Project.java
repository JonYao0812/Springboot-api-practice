package com.revolutionit.api.jobadder.jobadderapi.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Project {

    private String projectName;
    private String clientName;
    private String startDate;
    private String endDate;
    private Long employeeId;

    @Column
    @Id
    private String raId;
}
