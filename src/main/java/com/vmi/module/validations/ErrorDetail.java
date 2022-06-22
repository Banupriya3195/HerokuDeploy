package com.vmi.module.validations;

import java.io.Serializable;

public class ErrorDetail
  implements Serializable
{
  private static final long serialVersionUID = -1L;
  private String errorfield;
  private String errorfieldValue;
  private String errorDesc;
  
  public ErrorDetail() {}
  
  public ErrorDetail(String errorfield, String errorfieldValue, String errorDesc)
  {
    this.errorfield = errorfield;
    this.errorfieldValue = errorfieldValue;
    this.errorDesc = errorDesc;
  }
  
  public String getErrorfield()
  {
    return this.errorfield;
  }
  
  public void setErrorfield(String errorfield)
  {
    this.errorfield = errorfield;
  }
  
  public String getErrorfieldValue()
  {
    return this.errorfieldValue;
  }
  
  public void setErrorfieldValue(String errorfieldValue)
  {
    this.errorfieldValue = errorfieldValue;
  }
  
  public String getErrorDesc()
  {
    return this.errorDesc;
  }
  
  public void setErrorDesc(String errorDesc)
  {
    this.errorDesc = errorDesc;
  }
  
  public String toString()
  {
    return "ClassErrorDetail[errorfield = " + this.errorfield + " , errorfieldValue = " + this.errorfieldValue + ", errorDesc = " + this.errorDesc + "]";
  }
}
