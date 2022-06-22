package com.vmi.module.util;

import java.util.ArrayList;
import java.util.List;

public class AuthorizationException
  extends RuntimeException
{
  private final List<Error> errors = new ArrayList();
  
  public AuthorizationException(List<Error> errors)
  {
    this.errors.addAll(errors);
  }
  
  public AuthorizationException(Error error, Exception exception)
  {
    super(exception);
    this.errors.add(error);
  }
  
  public AuthorizationException(Exception exception)
  {
    super(exception);
  }
  
  public List<Error> getErrors()
  {
    return this.errors;
  }
}
