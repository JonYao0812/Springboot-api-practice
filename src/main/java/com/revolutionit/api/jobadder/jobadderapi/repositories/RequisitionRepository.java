package com.revolutionit.api.jobadder.jobadderapi.repositories;

import com.revolutionit.api.jobadder.jobadderapi.models.Requisition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequisitionRepository extends JpaRepository<Requisition, Long> {
  public Requisition findByName(String name);
}