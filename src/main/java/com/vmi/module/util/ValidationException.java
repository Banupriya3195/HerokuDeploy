package com.vmi.module.util;

import java.util.ArrayList;
import java.util.List;

public class ValidationException
  extends RuntimeException
{
  private final List<Error> errors = new ArrayList();
  
  public ValidationException(List<Error> errors)
  {
    this.errors.addAll(errors);
  }
  
  public ValidationException(Error error, Exception exception)
  {
    super(exception);
    this.errors.add(error);
  }
  
  public ValidationException(Exception exception)
  {
    super(exception);
  }
  
  public List<Error> getErrors()
  {
    return this.errors;
  }
}
