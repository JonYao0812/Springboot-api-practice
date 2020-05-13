package com.revolutionit.api.jobadder.jobadderapi.models;

import javax.persistence.GenerationType;
import javax.validation.constraints.Size;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Requisition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 3, max = 20)
    private String name;

    private String role;

  Requisition() {}

  public Requisition(String name, String role) {
      this.name = name;
      this.role = role;
    }
}
