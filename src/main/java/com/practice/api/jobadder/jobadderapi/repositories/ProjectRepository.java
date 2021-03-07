package com.practice.api.jobadder.jobadderapi.repositories;

import com.practice.api.jobadder.jobadderapi.models.Project;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, String> {
  public List<Project> findByClient(String client);

  public List<Project> findByEmployeeId(String id, Sort sort);

  @Override
  Project getOne(String raId);
}