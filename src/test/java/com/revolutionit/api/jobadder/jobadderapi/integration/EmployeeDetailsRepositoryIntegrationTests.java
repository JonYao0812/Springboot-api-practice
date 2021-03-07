package com.revolutionit.api.jobadder.jobadderapi.integration;

import com.practice.api.jobadder.jobadderapi.models.EmployeeDetails;
import com.practice.api.jobadder.jobadderapi.repositories.EmployeeRepository;
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
public class EmployeeDetailsRepositoryIntegrationTests {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private EmployeeRepository employeeRepository;

  @Test
  public void testFindByName() {

//    entityManager.persist(new Employee("Igor", "Trouble"));
//    entityManager.persist(new Employee("Steve", "Tester"));
//    entityManager.persist(new Employee("Dave", "Automator"));
//    entityManager.persist(new Employee("Lucky", "Useless Manager"));

    EmployeeDetails employeeDetails = employeeRepository.findByName("Steve");
    Assert.assertEquals("Steve", employeeDetails.getName());
  }

  @Test
  public void testFindAll() {

//    entityManager.persist(new Employee("Igor", "Trouble"));
//    entityManager.persist(new Employee("Steve", "Tester"));
//    entityManager.persist(new Employee("Dave", "Automator"));
//    entityManager.persist(new Employee("Lucky", "Useless Manager"));

    List<EmployeeDetails> employeeDetails = employeeRepository.findAll();
    Assert.assertEquals(4, employeeDetails.size());
  }

}