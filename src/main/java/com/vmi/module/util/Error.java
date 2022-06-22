package com.vmi.module.util;

public class Error
{
  private String errorfield;
  private String errorDesc;
  
  public Error() {}
  
  public Error(String errorfield, String errorDesc)
  {
    this.errorfield = errorfield;
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
  
  public String getErrorDesc()
  {
    return this.errorDesc;
  }
  
  public void setErrorDesc(String errorDesc)
  {
    this.errorDesc = errorDesc;
  }
}
