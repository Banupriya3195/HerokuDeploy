package com.vmi.module.service;

import com.vmi.module.model.WareHouseMapping;
import java.util.Map;
import javax.servlet.http.HttpSession;

public abstract interface WareHouseMappingService
{
  public abstract Map<String, Object> saveDetails(WareHouseMapping paramWareHouseMapping, HttpSession paramHttpSession);
  
  public abstract Map<String, Object> getById(String paramString);
  
  public abstract Map<String, Object> getListWareHouseMapping(String paramString);
  
  public abstract Map<String, Object> deleteById(String paramString);
}
