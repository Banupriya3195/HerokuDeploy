package com.vmi.module.service;

import com.vmi.module.model.Applicationproperties;
import java.util.Map;

public abstract interface ApplicationPropertiesService
{
  public abstract Map<String, Object> saveDetails(Applicationproperties paramApplicationproperties);
}
