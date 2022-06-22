package com.vmi.module.auditing;

public class AuditUser
{
  private static String userName;
  
  public static String getUserName()
  {
    return userName;
  }
  
  public static void setUserName(String userName)
  {
    AuditUser.userName = userName;
  }
}
