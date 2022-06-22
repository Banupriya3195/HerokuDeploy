package com.vmi.module.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vmi.module.model.Roles;
import com.vmi.module.repo.RolesRepo;

@Transactional
@Service("RolesService")
public class RolesServiceImpl
  implements RolesService
{
  @Autowired
  private RolesRepo rolesRepo;
  ObjectMapper objMapper = new ObjectMapper();
  private static final Logger LOGGER = LoggerFactory.getLogger(RolesServiceImpl.class);
  
  public Map<String, Object> saveDetails(Roles reqObj, HttpSession paramHttpSession)
  {
    Map<String, Object> responseHashMap = new HashMap();
    try
    {
      this.objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      Roles roles = this.rolesRepo.findById(reqObj.getId());
      if (roles != null)
      {
        reqObj.setCreateDate(roles.getCreateDate());
        reqObj.setCreateBy(roles.getCreateBy());
        roles = (Roles)this.objMapper.convertValue(reqObj, Roles.class);
        responseHashMap.put("message", "data updated !!");
      }
      else
      {
        reqObj.setId(null);
        roles = (Roles)this.objMapper.convertValue(reqObj, Roles.class);
        responseHashMap.put("message", "data inserted !!");
      }
      roles = (Roles)this.rolesRepo.save(roles);
      responseHashMap.put("roles", roles);
    }
    catch (Exception e)
    {
      LOGGER.error("Provider : RolesServiceImpl /n saverolesdetails : {} /n Exception : ", new Object[] { e });
    }
    return responseHashMap;
  }
  
  public Map<String, Object> getRoles()
  {
    Map<String, Object> responseDataHashMap = new HashMap();
    List<Roles> rolesObj = this.rolesRepo.findByRoleNotIn("ADMIN");
    if (rolesObj != null) {
      responseDataHashMap.put("roles", rolesObj);
    } else {
      responseDataHashMap.put("message", "No data found");
    }
    return responseDataHashMap;
  }
}
