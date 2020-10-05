package com.revolutionit.api.jobadder.jobadderapi.controllers;

import com.revolutionit.api.jobadder.jobadderapi.models.Employee;
import com.revolutionit.api.jobadder.jobadderapi.services.EmployeeService;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.revolutionit.api.jobadder.jobadderapi.support.RequisitionNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/consultants")
@RestController
class EmployeeController {
  @Autowired
  EmployeeService service;
  private List<Employee> filterdList;

//  @GetMapping("/requisitions")
//  List<Employee> all() {
//    return service.findAll();
//  }

//  @PostMapping("/requisitions")
//  Employee newRequisition(@RequestBody Employee newEmployee) {
//    return service.save(newEmployee);
//  }

  @GetMapping("/requisitions/{id}")
  @ResponseBody
  Optional<Employee> one(@PathVariable("id") String id) {
    System.out.println(id);
    return service.findOneById(id);
  }

//
//  @GetMapping("/requisition")
//  @ResponseBody
//  List<Employee> filterAll(@RequestParam(value = "location", required = false) String location) {
//    return service.findByLocation(location);
//  }

  @GetMapping("")
  @ResponseBody
  List<Employee> filteredAll(@RequestParam(value = "id", required = false) List<String> idParams,
                             @RequestParam(value = "name", required = false) String nameParam,
                             @RequestParam(value = "location", required = false) List<String> locationParams,
                             @RequestParam(value = "practice", required = false) List<String> practiceParams,
                             @RequestParam(value = "empType", required = false) List<String> empTypeParams,
                             @RequestParam(value = "subsidiary", required = false) List<String> subsidiaryParams) {
    this.filterdList = service.findAll();
    if(idParams != null && !idParams.isEmpty()){
      System.out.println(this.filterdList);
      System.out.println(practiceParams);
      this.filterdList = this.filterdList.stream().filter( employee -> idParams.contains(employee.getId())).collect(Collectors.toList());
    }
    if(nameParam != null && !nameParam.isEmpty()){
      this.filterdList = this.filterdList.stream().filter( employee -> employee.getName().contains(nameParam)).collect(Collectors.toList());
    }
    if(locationParams != null && !locationParams.isEmpty()){
      this.filterdList = this.filterdList.stream().filter( employee -> locationParams.contains(employee.getLocation())).collect(Collectors.toList());
    }
    if(practiceParams != null && !practiceParams.isEmpty()){
      this.filterdList = this.filterdList.stream().filter( employee -> practiceParams.contains(employee.getPractice())).collect(Collectors.toList());
    }
    if(empTypeParams != null && !empTypeParams.isEmpty()){
      this.filterdList = this.filterdList.stream().filter( employee -> empTypeParams.contains(employee.getEmpType())).collect(Collectors.toList());
    }
    if(subsidiaryParams != null && !subsidiaryParams.isEmpty()){
      this.filterdList = this.filterdList.stream().filter( employee -> subsidiaryParams.contains(employee.getSubsidiary())).collect(Collectors.toList());
    }


    return this.filterdList;
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
