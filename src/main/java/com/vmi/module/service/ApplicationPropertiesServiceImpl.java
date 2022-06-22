package com.vmi.module.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vmi.module.model.Applicationproperties;
import com.vmi.module.repo.ApplicationPropertiesRepo;

@Transactional
@Service("ApplicationPropertiesService")
public class ApplicationPropertiesServiceImpl
  implements ApplicationPropertiesService
{
  @Autowired
  private ApplicationPropertiesRepo applicationPropertiesRepo;
  @Autowired
  private SequenceGeneratorService sequenceGenerator;
  ObjectMapper objMapper = new ObjectMapper();
  private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationPropertiesServiceImpl.class);
  
  public Map<String, Object> saveDetails(Applicationproperties reqObj)
  {
    Map<String, Object> responseHashMap = new HashMap();
    try
    {
      this.objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      Applicationproperties applicationproperties = this.applicationPropertiesRepo.findByValId(Long.valueOf(reqObj.getValId()).longValue());
      if (applicationproperties != null)
      {
        reqObj.setCreateDate(applicationproperties.getCreateDate());
        reqObj.setCreateBy(applicationproperties.getCreateBy());
        applicationproperties = (Applicationproperties)this.objMapper.convertValue(reqObj, Applicationproperties.class);
        responseHashMap.put("message", "data updated !!");
      }
      else
      {
        reqObj.setValId(this.sequenceGenerator.generateSequence("Property_sequence"));
        applicationproperties = (Applicationproperties)this.objMapper.convertValue(reqObj, Applicationproperties.class);
        responseHashMap.put("message", "data inserted !!");
      }
      applicationproperties = (Applicationproperties)this.applicationPropertiesRepo.save(applicationproperties);
      responseHashMap.put("applicationproperties", applicationproperties);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return responseHashMap;
  }
}
