package com.vmi.module.util;

public class CustomErr
{
  private String errorMessage;
  private String errorCode;
  
  public CustomErr(String errorCode, String errorMessage)
  {
    this.errorMessage = errorMessage;
    this.errorCode = errorCode;
  }
  
  public String getErrorCode()
  {
    return this.errorCode;
  }
  
  public void setErrorCode(String errorCode)
  {
    this.errorCode = errorCode;
  }
  
  public void setErrorMessage(String errorMessage)
  {
    this.errorMessage = errorMessage;
  }
  
  public CustomErr(String errorMessage)
  {
    this.errorMessage = errorMessage;
  }
  
  public String getErrorMessage()
  {
    return this.errorMessage;
  }
}
