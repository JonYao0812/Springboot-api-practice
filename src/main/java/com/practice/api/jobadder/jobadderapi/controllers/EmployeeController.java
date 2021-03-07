package com.practice.api.jobadder.jobadderapi.controllers;

import com.practice.api.jobadder.jobadderapi.models.*;
import com.practice.api.jobadder.jobadderapi.models.EmployeeDetails;
import com.practice.api.jobadder.jobadderapi.models.Project;
import com.practice.api.jobadder.jobadderapi.services.EmployeeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.practice.api.jobadder.jobadderapi.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/employees")
@RestController
class EmployeeController {
  @Autowired
  EmployeeService service;
  @Autowired
  ProjectService projectService;

  private List<EmployeeDetails> filterdEmpDetailsList;
  private List<Employee> filteredEmployeeList = new ArrayList<Employee>();
  private Employee employeeObj;


//  @PostMapping("/requisitions")
//  Employee newRequisition(@RequestBody Employee newEmployee) {
//    return service.save(newEmployee);
//  }

  @GetMapping("/{id}")
  @ResponseBody
  Optional<EmployeeDetails> one(@PathVariable("id") String id) {
    return service.findOneById(id);
  }


  @GetMapping("")
  @ResponseBody
  List<Employee> filteredAll(@RequestParam(value = "id", required = false) List<String> idParams,
                                    @RequestParam(value = "name", required = false) String nameParam,
                                    @RequestParam(value = "location", required = false) List<Integer> locationParams,
                                    @RequestParam(value = "practice", required = false) List<Integer> practiceParams,
                                    @RequestParam(value = "empType", required = false) List<Integer> empTypeParams,
                                    @RequestParam(value = "subsidiary", required = false) List<Integer> subsidiaryParams,
                                    @RequestParam(value = "currentClient", required = false) List<Integer> currentClientParams) {
    this.filteredEmployeeList.clear();
    this.filterdEmpDetailsList = service.findAll();
    this.filterdEmpDetailsList.forEach(employeeDetailObj ->{
      this.employeeObj = Employee.builder()
              .employeeDetails(employeeDetailObj)
              .projects( projectService.findProjectsByEmployeeId( employeeDetailObj.getId() ,Sort.by(Sort.Direction.ASC,"raId")) )
              .build();
      this.filteredEmployeeList.add(this.employeeObj);
    });

    if(idParams != null && !idParams.isEmpty()){
      this.filteredEmployeeList = this.filteredEmployeeList.stream().filter(employee -> idParams.contains(employee.getEmployeeDetails().getId())).collect(Collectors.toList());
    }
    if(nameParam != null && !nameParam.isEmpty()){
      this.filteredEmployeeList = this.filteredEmployeeList.stream().filter(employee -> employee.getEmployeeDetails().getName().contains(nameParam)).collect(Collectors.toList());
    }
    if(locationParams != null && !locationParams.isEmpty()){
      this.filteredEmployeeList = this.filteredEmployeeList.stream().filter(employee -> locationParams.contains(employee.getEmployeeDetails().getLocation())).collect(Collectors.toList());
    }
    if(practiceParams != null && !practiceParams.isEmpty()){
      this.filteredEmployeeList = this.filteredEmployeeList.stream().filter(employee -> practiceParams.contains(employee.getEmployeeDetails().getPractice())).collect(Collectors.toList());
    }
    if(empTypeParams != null && !empTypeParams.isEmpty()){
      this.filteredEmployeeList = this.filteredEmployeeList.stream().filter(employee -> empTypeParams.contains(employee.getEmployeeDetails().getEmpType())).collect(Collectors.toList());
    }
    if(subsidiaryParams != null && !subsidiaryParams.isEmpty()){
      this.filteredEmployeeList = this.filteredEmployeeList.stream().filter(employee -> subsidiaryParams.contains(employee.getEmployeeDetails().getSubsidiary())).collect(Collectors.toList());
    }
    if(currentClientParams != null && !currentClientParams.isEmpty()){
//      this.filteredEmployeeList = this.filteredEmployeeList.stream().filter(employee -> currentClientParams.contains(employee.getEmployeeDetails().getSubsidiary())).collect(Collectors.toList());
    }

    List<Project> pro = projectService.findAll();
    System.out.println(pro);
    return this.filteredEmployeeList;
  }


//  @PutMapping("/requisitions/{id}")
//  Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
//
//    return service.replaceRequisitionById(newEmployee, id).orElseThrow(() -> new RequisitionNotFoundException(id));
//  }
//
//  @DeleteMapping("/requisitions/{id}")
//  void deleteEmployee(@PathVariable Long id) {
//    service.deleteById(id);
//  }

}
