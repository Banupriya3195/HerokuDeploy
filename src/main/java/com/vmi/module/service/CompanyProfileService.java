package com.vmi.module.service;

import com.vmi.module.model.CompanyProfile;
import com.vmi.module.model.Users;
import java.util.Map;
import javax.servlet.http.HttpSession;

public abstract interface CompanyProfileService
{
  public abstract Map<String, Object> saveDetails(CompanyProfile paramCompanyProfile, HttpSession paramHttpSession, Users paramUsers);
  
  public abstract Map<String, Object> getAllCompanyDetails(Users paramUsers);
  
  public abstract Map<String, Object> getById(String paramString);
  
  public abstract Map<String, Object> deleteCompanyById(String paramString);
}
