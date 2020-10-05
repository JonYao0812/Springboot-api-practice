package com.revolutionit.api.jobadder.jobadderapi.services;

import com.revolutionit.api.jobadder.jobadderapi.models.Employee;
import com.revolutionit.api.jobadder.jobadderapi.models.Project;
import com.revolutionit.api.jobadder.jobadderapi.repositories.EmployeeRepository;
import com.revolutionit.api.jobadder.jobadderapi.repositories.ProjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProjectService {
  @Autowired
  ProjectRepository projectRepo;

  @Cacheable("projects")
  public List<Project> findAll(){
    log.info("RequisitionService: findAll");
    simulateSlowService();
    return projectRepo.findAll();
  }

  public Optional<Project> findOneById(Long id){
    simulateSlowService();
    return projectRepo.findById(id);
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
