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
import com.vmi.module.model.IndentStatusTrack;
import com.vmi.module.repo.BillingShippingRepo;
import com.vmi.module.util.CustomErr;

@Transactional
@Service("BillShippingService")
public class BillShippingServiceImpl
  implements BillShippingService
{
  ObjectMapper objMapper = new ObjectMapper();
  private static final Logger LOGGER = LoggerFactory.getLogger(BillShippingServiceImpl.class);
  @Autowired
  private BillingShippingRepo billingShippingRepo;
  
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
    Map<String, Object> reqtDataHashMap = (Map)requestParams.get("companyadrress");
    try
    {
      IndentStatusTrack billingAddressObj = this.billingShippingRepo.findById((String)reqtDataHashMap.get("id"));
      if (billingAddressObj != null)
      {
        reqtDataHashMap.put("billingCreateDate", billingAddressObj.getCreateDate());
        this.objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        billingAddressObj = (IndentStatusTrack)this.objMapper.convertValue(reqtDataHashMap, IndentStatusTrack.class);
      }
      else
      {
        reqtDataHashMap.remove("id");
        this.objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        billingAddressObj = (IndentStatusTrack)this.objMapper.convertValue(reqtDataHashMap, IndentStatusTrack.class);
      }
      billingAddressObj = (IndentStatusTrack)this.billingShippingRepo.save(billingAddressObj);
      dataHashMap.put("billingAddress", billingAddressObj);
      populateSuccessResponse(responseHashMap, dataHashMap);
    }
    catch (Exception e)
    {
    	LOGGER.error("Provider : BillShippingServiceImpl /n saveCompanyadrees : {} /n Exception : ", new Object[] { e });
      
      dataHashMap.put("errorData", new CustomErr("LP - 10003", "Request has invalid data,Please try again"));
      
      populateFailureResponse(responseHashMap, dataHashMap);
    }
    return responseHashMap;
  }
  
  public Map<String, Object> getCompanyAdress(String id)
  {
    Map<String, Object> responseDataHashMap = new HashMap();
    Map<String, Object> dataHashMap = new HashMap();
    IndentStatusTrack billingAddress = this.billingShippingRepo.findById(id.trim());
    if (billingAddress != null)
    {
      dataHashMap.put("billingAddress", billingAddress);
      populateSuccessResponse(responseDataHashMap, dataHashMap);
    }
    else
    {
      populateFailureResponse(responseDataHashMap, dataHashMap);
    }
    return responseDataHashMap;
  }
}
