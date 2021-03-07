package com.revolutionit.api.jobadder.jobadderapi.support;

public class RequisitionNotFoundException extends RuntimeException {

  public RequisitionNotFoundException(Long id) {
    super("Could not find employee " + id);
  }
}