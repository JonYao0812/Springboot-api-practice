package com.practice.api.jobadder.jobadderapi.services;

import com.practice.api.jobadder.jobadderapi.models.Project;
import com.practice.api.jobadder.jobadderapi.repositories.ProjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProjectService {
  @Autowired
  ProjectRepository projectRepo;

  @Cacheable("projects")
  public List<Project> findAll(){
    log.info("Project Service: findAll");
//    simulateSlowService();
    return projectRepo.findAll();
  }

  public List<Project> findProjectsByEmployeeId(String id, Sort sort){
//    simulateSlowService();
    return projectRepo.findByEmployeeId(id, sort);
  }

  public List<Project> findProjectsByClient(String client){
//    simulateSlowService();
    return projectRepo.findByClient(client);
  }

  public Project save(Project newProject){
    return projectRepo.save(newProject);
  }

  public void delete(Project existingProject){
    projectRepo.delete(existingProject);
  }

//  public void deleteById(Long id) {
//    log.info("RequisitionService: delete by id");
//    projectRepo.deleteById(id);
//  }

//  public Optional<Employee> replaceRequisitionById(Employee newEmployee, Long id)
//  {
//    projectRepo.findById(id)
//        .map(requisition -> {
//          requisition.setName(newEmployee.getName());
////          requisition.setRole(newEmployee.getRole());
//          return projectRepo.save(requisition);
//        })
//        .orElseGet(() -> {
//          newEmployee.setId(id);
//          return projectRepo.save(newEmployee);
//        });
//    return null;
//  }

  private void simulateSlowService() {
    try {
      long time = 3000L;
      Thread.sleep(time);
    } catch (InterruptedException e) {
      throw new IllegalStateException(e);
    }
  }

}
