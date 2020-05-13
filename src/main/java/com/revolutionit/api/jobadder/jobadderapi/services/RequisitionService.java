package com.revolutionit.api.jobadder.jobadderapi.services;

import com.revolutionit.api.jobadder.jobadderapi.models.Requisition;
import com.revolutionit.api.jobadder.jobadderapi.repositories.RequisitionRepository;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RequisitionService {
  @Autowired
  RequisitionRepository repo;

  @Cacheable("requisitions")
  public List<Requisition> findAll(){
    log.info("RequisitionService: findAll");
    simulateSlowService();
    return repo.findAll();
  }

  @Cacheable("requisitions")
  public Optional<Requisition> findOneById(Long id){
    simulateSlowService();
    log.info("RequisitionService: findOne");
    return repo.findById(id);
  }

  public Requisition save(Requisition newRequisition){
    log.info("RequisitionService: save");
    return repo.save(newRequisition);
  }

  public void delete(Requisition existingRequisition){
    log.info("RequestionService: delete");
    repo.delete(existingRequisition);
  }

  public void deleteById(Long id) {
    log.info("RequisitionService: delete by id");
    repo.deleteById(id);
  }

  public Optional<Requisition> replaceRequisitionById(Requisition newRequisition, Long id)
  {
    repo.findById(id)
        .map(requisition -> {
          requisition.setName(newRequisition.getName());
          requisition.setRole(newRequisition.getRole());
          return repo.save(requisition);
        })
        .orElseGet(() -> {
          newRequisition.setId(id);
          return repo.save(newRequisition);
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

}
