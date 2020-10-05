package com.revolutionit.api.jobadder.jobadderapi.support;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revolutionit.api.jobadder.jobadderapi.models.Employee;
import com.revolutionit.api.jobadder.jobadderapi.models.Project;
import com.revolutionit.api.jobadder.jobadderapi.repositories.EmployeeRepository;
import com.revolutionit.api.jobadder.jobadderapi.services.EmployeeService;
import lombok.extern.slf4j.Slf4j;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.io.FileReader;
import java.nio.file.Paths;
import java.util.*;
import com.revolutionit.api.jobadder.jobadderapi.helper.*;

@Configuration
@Slf4j
class LoadDatabase {
  @Autowired
  EmployeeRepository employeeRepo;
  @Autowired
  EmployeeService employeeService;

  private Employee employeeObj;
  private Project projectObj;
  DateHelper dateHelper = new DateHelper();


  @Bean
  CommandLineRunner initDatabase(EmployeeRepository repository) {

    String fileName = "sb_employee.json";
    String filePath = Paths.get("cannedData", fileName).toAbsolutePath().toString();
    ObjectMapper objectMapper = new ObjectMapper();
    HashMap<String,String> map = new HashMap();

    return args -> {
        log.info("JSON PATH "+filePath);
      JSONParser parser = new JSONParser();
      try {
        //working version with Parse JSON
        Object obj = parser.parse(new FileReader(filePath));
        JSONArray employeeList = (JSONArray) obj;
        employeeList.forEach( emp ->{

          JSONObject employeeRecord = (JSONObject) ((JSONObject) emp).get("values");
//          String resourceAllocationId = jsonDeserializer(employeeRecord, "CUSTRECORD_PR_LR_EMPLOYEE.custrecord_pr_lr_end");
//
//          if( !resourceAllocationId.isEmpty()){
//            this.projectObj = Project.builder()
//                    .clientName( jsonDeserializer(employeeRecord, "resourceAllocation.customer" , "text"))
//                    .projectName( jsonDeserializer(employeeRecord, "resourceAllocation.company" , "text").substring(5) )
//                    .startDate(jsonDeserializer(employeeRecord, "resourceAllocation.startdate") )
//                    .endDate( jsonDeserializer(employeeRecord, "resourceAllocation.enddate") )
//                    .raId( jsonDeserializer(employeeRecord, "resourceAllocation.internalid", "value") )
//                    .build();
//          }

          String employeeId = (String) ((JSONObject) emp).get("id");
          Optional<Employee> currentEmployee = employeeService.findOneById(employeeId);

          if( currentEmployee.isEmpty()){
            this.employeeObj = Employee.builder()
                    .id(employeeId)
                    .name( jsonDeserializer(employeeRecord, "entityid") )
                    .location( jsonDeserializer(employeeRecord, "location" , "value"))
                    .practice( jsonDeserializer(employeeRecord, "departmentnohierarchy", "value") )
                    .empType( jsonDeserializer(employeeRecord, "employeetype", "value") )
                    .probation(new HashMap<>(Map.of("status","2",
                            "probationNoticeDate", dateHelper.calculateDate(jsonDeserializer(employeeRecord, "hiredate"), 5, "month"),
                                    "probationDate", dateHelper.calculateDate(jsonDeserializer(employeeRecord, "hiredate"), 6, "month")
                    )
                    ) )
                    .subsidiary(jsonDeserializer(employeeRecord, "subsidiarynohierarchy", "value"))
                    .build();
            employeeRepo.save(this.employeeObj);
          }

        log.info("EMP "+ this.employeeObj);
//          log.info("pro "+ this.projectObj.toString());

        });

//        ObjectMapper mapper = new ObjectMapper();
//        Object obj = parser.parse(new FileReader(filePath));
//        List<Employee> empLists = Arrays.asList(mapper.readValue(obj.toString(), Employee[].class));
//
//        empLists.forEach( emp ->{
//          System.out.println("EMP "+ emp.toString());
//        });


      } catch (Exception e) {
        e.printStackTrace();
      }
//      log.info("Preloading " + repository.save(new Employee("Bilbo Baggins", "burglar")));
//      log.info("Preloading " + repository.save(new Employee("Frodo Baggins", "thief")));
      };

    }

  private String jsonDeserializer(JSONObject dataObj, String key) {
    return ( dataObj.get(key).toString() != null ) ? dataObj.get(key).toString() : "";
  }

  private HashMap jsonMapDeserializer(JSONObject dataObj, String key) {
    return (HashMap) ((JSONArray)dataObj.get(key)).get(0);
  }

  private String jsonDeserializer(JSONObject dataObj, String key, String valueKey){
    JSONObject innerObj = (JSONObject) ((JSONArray)dataObj.get(key)).get(0);
    return (innerObj.get(valueKey)).toString();
  }




}