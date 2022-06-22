package com.vmi.module.service;

import com.vmi.module.model.Users;
import java.util.Map;
import javax.servlet.http.HttpSession;

public abstract interface UsersService
{
  public abstract Map<String, Object> saveDetails(Users paramUsers, HttpSession paramHttpSession);
  
  public abstract Map<String, Object> getById(String paramString);
  
  public abstract Map<String, Object> getListUsers();
  
  public abstract Map<String, Object> deleteByUserId(String paramString);
}
