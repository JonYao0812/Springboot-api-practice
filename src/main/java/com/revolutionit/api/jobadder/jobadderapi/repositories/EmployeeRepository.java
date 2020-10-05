package com.revolutionit.api.jobadder.jobadderapi.repositories;

import com.revolutionit.api.jobadder.jobadderapi.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
  public Employee findByName(String name);

  List<Employee> findByLocation(String location);

}