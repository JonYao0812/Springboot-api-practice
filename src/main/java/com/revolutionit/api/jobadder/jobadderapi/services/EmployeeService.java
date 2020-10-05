package com.revolutionit.api.jobadder.jobadderapi.services;

import com.revolutionit.api.jobadder.jobadderapi.models.Employee;
import com.revolutionit.api.jobadder.jobadderapi.repositories.EmployeeRepository;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmployeeService {
  @Autowired
  EmployeeRepository repo;

  public List<Employee> findAll(){
    log.info("RequisitionService: findAll");
    simulateSlowService();
    return repo.findAll();
  }

  public Optional<Employee> findOneById(String id){
//    simulateSlowService();
    log.info("RequisitionService: find by id " + id);
    return repo.findById(id);
  }


  public Employee save(Employee newEmployee){
    log.info("RequisitionService: save");
    return repo.save(newEmployee);
  }

  public void delete(Employee existingEmployee){
    log.info("RequestionService: delete");
    repo.delete(existingEmployee);
  }

  public void deleteById(String id) {
    log.info("RequisitionService: delete by id");
    repo.deleteById(id);
  }

  public Optional<Employee> replaceRequisitionById(Employee newEmployee, String id)
  {
    repo.findById(id)
        .map(requisition -> {
          requisition.setName(newEmployee.getName());
//          requisition.setRole(newEmployee.getRole());
          return repo.save(requisition);
        })
        .orElseGet(() -> {
          newEmployee.setId(id);
          return repo.save(newEmployee);
        });
    return null;
  }

  private void simulateSlowService() {
    try {
      long time = 3000L;
      Thread.sleep(time);
    } catch (InterruptedException e) {
      throw new IllegalStateException(e);
    }
  }

  public List<Employee> findByLocation(String location) {
    simulateSlowService();
    return repo.findByLocation(location);
  }
}
