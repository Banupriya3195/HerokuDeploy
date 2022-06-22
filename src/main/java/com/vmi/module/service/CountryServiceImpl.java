package com.vmi.module.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vmi.module.model.Countries;
import com.vmi.module.repo.CountryRepo;
import com.vmi.module.util.CustomErr;

@Transactional
@Service("CountryService")
public class CountryServiceImpl
  implements CountryService
{
  @Autowired
  private CountryRepo countryRepo;
  ObjectMapper objMapper = new ObjectMapper();
  
  private static final Logger LOGGER = LoggerFactory.getLogger(CountryServiceImpl.class);
  
  private void populateSuccessResponse(Map<String, Object> responseHashMap, Map<String, Object> dataHashMap)
  {
    responseHashMap.put("success", HttpStatus.OK);
    responseHashMap.put("response", dataHashMap);
  }
  
  private void populateFailureResponse(Map<String, Object> responseHashMap, Map<String, Object> dataHashMap)
  {
    responseHashMap.put("success", Boolean.valueOf(false));
    responseHashMap.put("response", dataHashMap);
  }
  
  public Map<String, Object> saveDetails(Map<?, ?> requestParams, HttpSession paramHttpSession)
  {
    Map<String, Object> responseHashMap = new HashMap();
    Map<String, Object> dataHashMap = new HashMap();
    Map<String, Object> reqtDataHashMap = (Map)requestParams.get("countrydetails");
    try
    {
      this.objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      Countries countriesObj = this.countryRepo.findById((String)reqtDataHashMap.get("id"));
      if (countriesObj != null)
      {
        reqtDataHashMap.put("createDate", countriesObj.getCreateDate());
        reqtDataHashMap.put("createBy", countriesObj.getCreateBy());
        countriesObj = (Countries)this.objMapper.convertValue(reqtDataHashMap, Countries.class);
        countriesObj.setActionMessage("data updated!!");
      }
      else
      {
        reqtDataHashMap.remove("id");
        countriesObj = (Countries)this.objMapper.convertValue(reqtDataHashMap, Countries.class);
        countriesObj.setActionMessage("data inserted!!");
      }
      countriesObj = (Countries)this.countryRepo.save(countriesObj);
      dataHashMap.put("countrydetails", countriesObj);
      populateSuccessResponse(responseHashMap, dataHashMap);
    }
    catch (Exception e)
    {
    	LOGGER.error("Provider : CountryServiceImpl /n savecountrydetails : {} /n Exception : ", new Object[] { e });
      dataHashMap.put("errorData", new CustomErr("LP - 10003", "Request has invalid data,Please try again"));
      populateFailureResponse(responseHashMap, dataHashMap);
    }
    return responseHashMap;
  }
  
  public Map<String, Object> getById(String id)
  {
    Map<String, Object> responseDataHashMap = new HashMap();
    Map<String, Object> dataHashMap = new HashMap();
    Countries countriesObj = this.countryRepo.findById(id.trim());
    if (countriesObj != null)
    {
      dataHashMap.put("countrydetails", countriesObj);
      populateSuccessResponse(responseDataHashMap, dataHashMap);
    }
    else
    {
      populateFailureResponse(responseDataHashMap, dataHashMap);
    }
    return responseDataHashMap;
  }
}
