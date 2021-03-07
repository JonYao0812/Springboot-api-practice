package com.practice.api.jobadder.jobadderapi.support;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.api.jobadder.jobadderapi.models.EmployeeDetails;
import com.practice.api.jobadder.jobadderapi.models.Location;
import com.practice.api.jobadder.jobadderapi.models.Project;
import com.practice.api.jobadder.jobadderapi.repositories.EmployeeRepository;
import com.practice.api.jobadder.jobadderapi.repositories.ProjectRepository;
import com.practice.api.jobadder.jobadderapi.services.EmployeeService;
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
import com.practice.api.jobadder.jobadderapi.helper.*;

@Configuration
@Slf4j
class LoadDatabase {
  @Autowired
  EmployeeRepository employeeRepo;
  @Autowired
  EmployeeService employeeService;
  @Autowired
  ProjectRepository projectRepo;


  private EmployeeDetails employeeDetailsObj;
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
          String employeeId = (String) ((JSONObject) emp).get("id");
          Optional<EmployeeDetails> currentEmployee = employeeService.findOneById(employeeId);

          if( currentEmployee.isEmpty()){
            this.employeeDetailsObj = EmployeeDetails.builder()
                    .id(employeeId)
                    .name( jsonDeserializer(employeeRecord, "entityid") )
                    .location( Integer.valueOf(jsonDeserializer(employeeRecord, "location" , "value")))
                    .practice( Integer.valueOf(jsonDeserializer(employeeRecord, "departmentnohierarchy", "value")) )
                    .empType( Integer.valueOf(jsonDeserializer(employeeRecord, "employeetype", "value")) )
//                    .probation(new HashMap<>(Map.of("status","2",
//                            "probationNoticeDate", dateHelper.calculateDate(jsonDeserializer(employeeRecord, "hiredate"), 5, "month"),
//                                    "probationDate", dateHelper.calculateDate(jsonDeserializer(employeeRecord, "hiredate"), 6, "month"))))
                    .subsidiary( Integer.valueOf(jsonDeserializer(employeeRecord, "subsidiarynohierarchy", "value")))
                    .build();
            if(!jsonDeserializer(employeeRecord, "hiredate").isEmpty()){
              HashMap probationMap = new HashMap();
              probationMap.put("status", Integer.valueOf(jsonDeserializer(employeeRecord, "employeestatus","value")));
              probationMap.put("probationNoticeDate", dateHelper.calculateDate(jsonDeserializer(employeeRecord, "hiredate"), 5, "month"));
              probationMap.put("probationDate", dateHelper.calculateDate(jsonDeserializer(employeeRecord, "hiredate"), 6, "month"));
              this.employeeDetailsObj.setProbation(probationMap);
            }
            employeeRepo.save(this.employeeDetailsObj);

//            if( locationRepo.findById(Integer.valueOf(jsonDeserializer(employeeRecord, "location" , "value"))) == null ){
//              locationRepo.save(Location.builder()
//              .id( Integer.valueOf(jsonDeserializer(employeeRecord, "location" , "value")) )
//              .location( jsonDeserializer(employeeRecord, "location" , "text") )
//              .build());
//            }
          }
          log.info("EMP "+ this.employeeDetailsObj);

          String resourceAllocationId = jsonDeserializer(employeeRecord, "resourceAllocation.internalid","value");
          String client =  jsonDeserializer(employeeRecord, "resourceAllocation.customer" , "value");

          if( !resourceAllocationId.isEmpty() && !client.equals("194") && !projectRepo.existsById(resourceAllocationId) ){
            String startDateString = jsonDeserializer(employeeRecord, "resourceAllocation.startdate");
            String endDateStirng = jsonDeserializer(employeeRecord, "resourceAllocation.enddate");
            this.projectObj = Project.builder()
                    .client(client)
                    .projectName( jsonDeserializer(employeeRecord, "resourceAllocation.company" , "text").substring(5) )
                    .startDate( startDateString )
                    .endDate( endDateStirng )
                    .employeeId( employeeId )
                    .raId( jsonDeserializer(employeeRecord, "resourceAllocation.internalid", "value") )
                    .isCurrentProject( dateHelper.isCurrent(startDateString, endDateStirng))
                    .build();
            projectRepo.save(this.projectObj);
          }
          log.info("PRO "+ this.projectObj);
        });

//        ObjectMapper mapper = new ObjectMapper();
//        Object obj = parser.parse(new FileReader(filePath));
//        List<Employee> empLists = Arrays.asList(mapper.readValue(obj.toString(), Employee[].class));
//
//        empLists.forEach( emp ->{
//          System.out.println("EMP "+ emp.toString());
//        });
//      System.out.println(locationRepo.findAll());

      } catch (Exception e) {
        e.printStackTrace();
      }
      };

    }

  private String jsonDeserializer(JSONObject dataObj, String key) {
    return ( dataObj.get(key).toString() == null ) ? "" : dataObj.get(key).toString();
  }

  private HashMap jsonMapDeserializer(JSONObject dataObj, String key) {
    return (HashMap) ((JSONArray)dataObj.get(key)).get(0);
  }

  private String jsonDeserializer(JSONObject dataObj, String key, String valueKey){
    if(((JSONArray)dataObj.get(key)).isEmpty()){
      return "";
    }
    JSONObject innerObj = (JSONObject) ((JSONArray)dataObj.get(key)).get(0);
    return (innerObj.get(valueKey)).toString();
  }




}