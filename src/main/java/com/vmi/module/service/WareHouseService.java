package com.vmi.module.service;

import com.vmi.module.model.CompanyProfile;
import com.vmi.module.model.WareHouseDetails;
import java.util.Map;
import javax.servlet.http.HttpSession;

public abstract interface WareHouseService
{
  public abstract Map<String, Object> saveDetails(WareHouseDetails paramWareHouseDetails, HttpSession paramHttpSession);
  
  public abstract Map<String, Object> getWareHouseId(String paramString);
  
  public abstract Map<String, Object> deleteWarehouseDelete(String paramString);
  
  public abstract void saveDetails(CompanyProfile paramCompanyProfile, HttpSession paramHttpSession);
  
  public abstract Map<String, Object> getCompanywarehouse(String paramString);
}
