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
import com.vmi.module.model.Sch;
import com.vmi.module.repo.SupplyChainRepo;
import com.vmi.module.util.Error;
import com.vmi.module.util.ValidationException;

@Transactional
@Service("SupplyChainService")
public class SupplyChainServiceImpl
  implements SupplyChainService
{
  @Autowired
  private SupplyChainRepo supplyChainRepo;
  ObjectMapper objMapper = new ObjectMapper();
  
  public Map<String, Object> saveDetails(Sch reqObj, HttpSession session)
  {
    Map<String, Object> responseHashMap = new HashMap();
    List<Error> errorDetail = new ArrayList();
    try
    {
      this.objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      Sch schObj = this.supplyChainRepo.findById(reqObj.getId());
      if (schObj != null)
      {
        if ((!schObj.getWareHouse().getId().equals(reqObj.getWareHouse().getId())) || (!schObj.getParentWarehouseId().equals(reqObj.getParentWarehouseId())))
        {
          Sch schDupl = this.supplyChainRepo.findByWareHouseAndParentWarehouseId(reqObj.getWareHouse().getId(), reqObj.getParentWarehouseId());
          if (schDupl != null) {
            throw new ValidationException(errorDetail);
          }
        }
        reqObj.setCreateDate(schObj.getCreateDate());
        reqObj.setCreateBy(schObj.getCreateBy());
        schObj = (Sch)this.objMapper.convertValue(reqObj, Sch.class);
        responseHashMap.put("message", "data updated !!");
      }
      else
      {
        schObj = this.supplyChainRepo.findByWareHouseAndParentWarehouseId(reqObj.getWareHouse().getId(), reqObj.getParentWarehouseId());
        if (schObj != null) {
          throw new ValidationException(errorDetail);
        }
        reqObj.setId(null);
        schObj = (Sch)this.objMapper.convertValue(reqObj, Sch.class);
        responseHashMap.put("message", "data inserted !!");
      }
      schObj = (Sch)this.supplyChainRepo.save(schObj);
      responseHashMap.put("supplyChain", schObj);
    }
    catch (Exception e)
    {
      errorDetail.add(new Error("C002", "Requested warehouse already exist"));
      throw new ValidationException(errorDetail);
    }
    return responseHashMap;
  }
  
  public Map<String, Object> getById(String id)
  {
    Map<String, Object> responseDataHashMap = new HashMap();
    List<Sch> supplychainObj = this.supplyChainRepo.findByParentWarehouseId(id.trim());
    if (supplychainObj != null) {
      responseDataHashMap.put("supplyChain", supplychainObj);
    } else {
      responseDataHashMap.put("message", "No data found");
    }
    return responseDataHashMap;
  }
  
  public Map<String, Object> deleteBySchId(String id)
  {
    Map<String, Object> responseHashMap = new HashMap();
    Sch schObj = this.supplyChainRepo.findById(id.trim());
    if (schObj != null)
    {
      this.supplyChainRepo.delete(schObj);
      responseHashMap.put("message", "data deleted");
    }
    else
    {
      responseHashMap.put("message", "No data found");
    }
    return responseHashMap;
  }
}
