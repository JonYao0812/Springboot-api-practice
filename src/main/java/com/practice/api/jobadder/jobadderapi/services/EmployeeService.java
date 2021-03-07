package com.practice.api.jobadder.jobadderapi.services;

import com.practice.api.jobadder.jobadderapi.models.EmployeeDetails;
import com.practice.api.jobadder.jobadderapi.repositories.EmployeeRepository;
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

  public List<EmployeeDetails> findAll(){
    log.info("RequisitionService: findAll");
    simulateSlowService();
    return repo.findAll();
  }

  public Optional<EmployeeDetails> findOneById(String id){
//    simulateSlowService();
    log.info("RequisitionService: find by id " + id);
    return repo.findById(id);
  }


  public EmployeeDetails save(EmployeeDetails newEmployeeDetails){
    log.info("RequisitionService: save");
    return repo.save(newEmployeeDetails);
  }

  public void delete(EmployeeDetails existingEmployeeDetails){
    log.info("RequestionService: delete");
    repo.delete(existingEmployeeDetails);
  }

  public void deleteById(String id) {
    log.info("RequisitionService: delete by id");
    repo.deleteById(id);
  }

  public Optional<EmployeeDetails> replaceRequisitionById(EmployeeDetails newEmployeeDetails, String id)
  {
    repo.findById(id)
        .map(requisition -> {
          requisition.setName(newEmployeeDetails.getName());
//          requisition.setRole(newEmployee.getRole());
          return repo.save(requisition);
        })
        .orElseGet(() -> {
          newEmployeeDetails.setId(id);
          return repo.save(newEmployeeDetails);
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

  public List<EmployeeDetails> findByLocation(String location) {
    simulateSlowService();
    return repo.findByLocation(location);
  }
}
