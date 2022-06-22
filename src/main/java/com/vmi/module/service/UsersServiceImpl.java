package com.vmi.module.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vmi.module.model.Users;
import com.vmi.module.repo.UsersRepo;
import com.vmi.module.util.Error;
import com.vmi.module.util.ValidationException;
import com.vmi.module.validations.Helper;

@Transactional
@Service("UsersService")
public class UsersServiceImpl
  implements UsersService
{
  @Autowired
  private UsersRepo usersRepo;
  ObjectMapper objMapper = new ObjectMapper();
  
  public Map<String, Object> saveDetails(Users reqObj, HttpSession paramHttpSession)
  {
    Map<String, Object> responseHashMap = new HashMap();
    Users userObj = null;
    List<Error> errorDetail = new ArrayList();
    try
    {
      this.objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      if (reqObj.getId() != null) {
        userObj = this.usersRepo.findById(reqObj.getId());
      } else {
        userObj = null;
      }
      if (userObj != null)
      {
        reqObj.setCreateDate(userObj.getCreateDate());
        reqObj.setCreateBy(userObj.getCreateBy());
        reqObj.setLastLogin(userObj.getLastLogin());
        reqObj.setPassword(userObj.getPassword());
        reqObj.setAuthorization(userObj.getAuthorization());
        reqObj.setAuthorizationToken(userObj.getAuthorizationToken());
        userObj = (Users)this.objMapper.convertValue(reqObj, Users.class);
        responseHashMap.put("message", "data updated !!");
      }
      else
      {
        reqObj.setId(null);
        userObj = (Users)this.objMapper.convertValue(reqObj, Users.class);
        userObj.setLastLogin(Helper.getSystemDate());
        userObj.setPassword(new AuthenticateServiceImpl().encryptMessage("vmi1234"));
        responseHashMap.put("message", "data inserted !!");
      }
      userObj = (Users)this.usersRepo.save(userObj);
      responseHashMap.put("users", userObj);
    }
    catch (DuplicateKeyException e)
    {
      errorDetail.add(new Error("C004", "Duplicate values of User Name,Code,Email,phone number can not be allowed"));
      throw new ValidationException(errorDetail);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return responseHashMap;
  }
  
  public Map<String, Object> getById(String id)
  {
    Map<String, Object> responseDataHashMap = new HashMap();
    Users usersObj = this.usersRepo.findById(id.trim());
    if (usersObj != null) {
      responseDataHashMap.put("users", usersObj);
    } else {
      responseDataHashMap.put("message", "No data found");
    }
    return responseDataHashMap;
  }
  
  public Map<String, Object> getListUsers()
  {
    Map<String, Object> responseDataHashMap = new HashMap();
    List<Users> usersObj = this.usersRepo.findAll();
    if (usersObj != null) {
      responseDataHashMap.put("user", usersObj);
    } else {
      responseDataHashMap.put("message", "No data found");
    }
    return responseDataHashMap;
  }
  
  public Map<String, Object> deleteByUserId(String id)
  {
    Map<String, Object> responseHashMap = new HashMap();
    Users usersObj = this.usersRepo.findById(id.trim());
    if (usersObj != null)
    {
      this.usersRepo.delete(usersObj);
      responseHashMap.put("message", "data deleted");
    }
    else
    {
      responseHashMap.put("message", "No data found");
    }
    return responseHashMap;
  }
}
