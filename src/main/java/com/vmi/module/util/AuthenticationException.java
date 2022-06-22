package com.vmi.module.util;

import java.util.ArrayList;
import java.util.List;

public class AuthenticationException
  extends RuntimeException
{
  private final List<Error> errors = new ArrayList();
  
  public AuthenticationException(List<Error> errors)
  {
    this.errors.addAll(errors);
  }
  
  public AuthenticationException(Error error, Exception exception)
  {
    super(exception);
    this.errors.add(error);
  }
  
  public AuthenticationException(Exception exception)
  {
    super(exception);
  }
  
  public List<Error> getErrors()
  {
    return this.errors;
  }
}
