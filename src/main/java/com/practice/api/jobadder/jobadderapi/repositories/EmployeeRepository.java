package com.practice.api.jobadder.jobadderapi.repositories;

import com.practice.api.jobadder.jobadderapi.models.EmployeeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeDetails, String> {
  public EmployeeDetails findByName(String name);

  List<EmployeeDetails> findByLocation(String location);

}