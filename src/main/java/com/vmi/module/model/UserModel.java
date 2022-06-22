package com.vmi.module.model;

public class UserModel
{
  private String empCode;
  private String empPassword;
  private String newPassword;
  
  public String getEmpCode()
  {
    return this.empCode;
  }
  
  public void setEmpCode(String empCode)
  {
    this.empCode = empCode;
  }
  
  public String getEmpPassword()
  {
    return this.empPassword;
  }
  
  public void setEmpPassword(String empPassword)
  {
    this.empPassword = empPassword;
  }
  
  public String getNewPassword()
  {
    return this.newPassword;
  }
  
  public void setNewPassword(String newPassword)
  {
    this.newPassword = newPassword;
  }
}
