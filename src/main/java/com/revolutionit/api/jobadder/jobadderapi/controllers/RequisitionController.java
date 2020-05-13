package com.revolutionit.api.jobadder.jobadderapi.controllers;

import com.revolutionit.api.jobadder.jobadderapi.models.Requisition;
import com.revolutionit.api.jobadder.jobadderapi.services.RequisitionService;
import com.revolutionit.api.jobadder.jobadderapi.support.RequisitionNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class RequisitionController {
  @Autowired
  RequisitionService service;
//  RequisitionController(RequisitionRepository repo) {
//    this.repo = repo;
//  }

  @GetMapping("/requisitions")
  List<Requisition> all() {
    return service.findAll();
  }

  @PostMapping("/requisitions")
  Requisition newRequisition(@RequestBody Requisition newRequisition) {
    return service.save(newRequisition);
  }

  @GetMapping("/requisitions/{id}")
  Requisition one(@PathVariable Long id) {
    return service.findOneById(id).orElseThrow(() -> new RequisitionNotFoundException(id));
  }

  @PutMapping("/requisitions/{id}")
  Requisition replaceEmployee(@RequestBody Requisition newRequisition, @PathVariable Long id) {

    return service.replaceRequisitionById(newRequisition, id).orElseThrow(() -> new RequisitionNotFoundException(id));
  }

  @DeleteMapping("/requisitions/{id}")
  void deleteEmployee(@PathVariable Long id) {
    service.deleteById(id);
  }

}
