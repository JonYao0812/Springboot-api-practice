package com.revolutionit.api.jobadder.jobadderapi.support;

import com.revolutionit.api.jobadder.jobadderapi.models.Requisition;
import com.revolutionit.api.jobadder.jobadderapi.repositories.RequisitionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
class LoadDatabase {

  @Bean
  CommandLineRunner initDatabase(RequisitionRepository repository) {
    return args -> {
      log.info("Preloading " + repository.save(new Requisition("Bilbo Baggins", "burglar")));
      log.info("Preloading " + repository.save(new Requisition("Frodo Baggins", "thief")));
    };
  }
}