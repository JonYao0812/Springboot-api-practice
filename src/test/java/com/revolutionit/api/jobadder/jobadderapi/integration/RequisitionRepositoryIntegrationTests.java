package com.revolutionit.api.jobadder.jobadderapi.integration;

import com.revolutionit.api.jobadder.jobadderapi.models.Requisition;
import com.revolutionit.api.jobadder.jobadderapi.repositories.RequisitionRepository;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RequisitionRepositoryIntegrationTests {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private RequisitionRepository requisitionRepository;

  @Test
  public void testFindByName() {

    entityManager.persist(new Requisition("Igor", "Trouble"));
    entityManager.persist(new Requisition("Steve", "Tester"));
    entityManager.persist(new Requisition("Dave", "Automator"));
    entityManager.persist(new Requisition("Lucky", "Useless Manager"));

    Requisition requisition = requisitionRepository.findByName("Steve");
    Assert.assertEquals("Steve", requisition.getName());
  }

  @Test
  public void testFindAll() {

    entityManager.persist(new Requisition("Igor", "Trouble"));
    entityManager.persist(new Requisition("Steve", "Tester"));
    entityManager.persist(new Requisition("Dave", "Automator"));
    entityManager.persist(new Requisition("Lucky", "Useless Manager"));

    List<Requisition> requisitions = requisitionRepository.findAll();
    Assert.assertEquals(4, requisitions.size());
  }

}