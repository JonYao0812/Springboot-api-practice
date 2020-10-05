package com.revolutionit.api.jobadder.jobadderapi.repositories;

import com.revolutionit.api.jobadder.jobadderapi.models.Employee;
import com.revolutionit.api.jobadder.jobadderapi.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
  public Project findByClientName(String name);

  public List<Project> findByEmployeeId(Long id);
}