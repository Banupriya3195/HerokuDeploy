package com.vmi.module.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vmi.module.model.WareHouseMapping;
import com.vmi.module.repo.WareHouseMappingRepo;
import com.vmi.module.util.Error;
import com.vmi.module.util.ValidationException;

@Transactional
@Service("WareHouseMappingService")
public class WareHouseMappingServiceImpl
  implements WareHouseMappingService
{
  @Autowired
  private WareHouseMappingRepo wareHouseMappingRepo;
  ObjectMapper objMapper = new ObjectMapper();
  
  public Map<String, Object> saveDetails(WareHouseMapping reqObj, HttpSession paramHttpSession)
  {
    Map<String, Object> responseHashMap = new HashMap();
    List<Error> errorDetail = new ArrayList();
    try
    {
      this.objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      WareHouseMapping wareHouseMappingObj = null;
      if (reqObj.getId() != null) {
        wareHouseMappingObj = this.wareHouseMappingRepo.findById(reqObj.getId());
      } else {
        wareHouseMappingObj = null;
      }
      if (wareHouseMappingObj != null)
      {
        if ((!wareHouseMappingObj.getWareHouse().getId().equals(reqObj.getWareHouse().getId())) || (!wareHouseMappingObj.getItemMaster().getId().equals(reqObj.getItemMaster().getId())))
        {
          WareHouseMapping wareHouseMapDupl = this.wareHouseMappingRepo.findByWareHouseAndItemMaster(reqObj.getWareHouse().getId(), reqObj.getItemMaster().getId());
          if (wareHouseMapDupl != null) {
            throw new ValidationException(errorDetail);
          }
        }
        reqObj.setCreateDate(wareHouseMappingObj.getCreateDate());
        reqObj.setCreateBy(wareHouseMappingObj.getCreateBy());
        wareHouseMappingObj = (WareHouseMapping)this.objMapper.convertValue(reqObj, WareHouseMapping.class);
        wareHouseMappingObj.setGreenZone(reqObj.getItemMax() / 1L);
        wareHouseMappingObj.setYellowZone(reqObj.getItemMax() / 2L);
        wareHouseMappingObj.setRedZone(reqObj.getItemMax() / 3L);
        responseHashMap.put("message", "data updated !!");
      }
      else
      {
        wareHouseMappingObj = this.wareHouseMappingRepo.findByWareHouseAndItemMaster(reqObj.getWareHouse().getId(), reqObj.getItemMaster().getId());
        if (wareHouseMappingObj != null) {
          throw new ValidationException(errorDetail);
        }
        reqObj.setId(null);
        wareHouseMappingObj = (WareHouseMapping)this.objMapper.convertValue(reqObj, WareHouseMapping.class);
        wareHouseMappingObj.setGreenZone(reqObj.getItemMax() / 1L);
        wareHouseMappingObj.setYellowZone(reqObj.getItemMax() / 2L);
        wareHouseMappingObj.setRedZone(reqObj.getItemMax() / 3L);
        responseHashMap.put("message", "data inserted !!");
      }
      wareHouseMappingObj = (WareHouseMapping)this.wareHouseMappingRepo.save(wareHouseMappingObj);
      responseHashMap.put("wareHouseMapping", wareHouseMappingObj);
    }
    catch (Exception e)
    {
      errorDetail.add(new Error("C002", "Requested warehouse and item  already mapped"));
      throw new ValidationException(errorDetail);
    }
    return responseHashMap;
  }
  
  public Map<String, Object> getById(String id)
  {
    Map<String, Object> responseDataHashMap = new HashMap();
    WareHouseMapping wareHouseMappingObj = this.wareHouseMappingRepo.findById(id.trim());
    if (wareHouseMappingObj != null) {
      responseDataHashMap.put("wareHouseMapping", wareHouseMappingObj);
    } else {
      responseDataHashMap.put("message", "No data found");
    }
    return responseDataHashMap;
  }
  
  public Map<String, Object> getListWareHouseMapping(String id)
  {
    Map<String, Object> responseDataHashMap = new HashMap();
    List<WareHouseMapping> wareHouseMappingObj = this.wareHouseMappingRepo.findByWareHouse(id.trim());
    if (wareHouseMappingObj != null) {
      responseDataHashMap.put("wareHouseMapping", wareHouseMappingObj);
    } else {
      responseDataHashMap.put("message", "No data found");
    }
    return responseDataHashMap;
  }
  
  public Map<String, Object> deleteById(String id)
  {
    Map<String, Object> responseDataHashMap = new HashMap();
    WareHouseMapping wareHouseMappingObj = this.wareHouseMappingRepo.findById(id.trim());
    if (wareHouseMappingObj != null)
    {
      this.wareHouseMappingRepo.delete(wareHouseMappingObj);
      responseDataHashMap.put("message", "data deleted");
    }
    else
    {
      responseDataHashMap.put("message", "No data found");
    }
    return responseDataHashMap;
  }
}
