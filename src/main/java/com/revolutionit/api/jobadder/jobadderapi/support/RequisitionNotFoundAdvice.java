package com.revolutionit.api.jobadder.jobadderapi.support;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class RequisitionNotFoundAdvice {

  @ResponseBody
  @ExceptionHandler(RequisitionNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String requisitionNotFoundHandler(RequisitionNotFoundException ex) {
    return ex.getMessage();
  }
}