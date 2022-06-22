package com.vmi.module.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vmi.module.model.ItemCategory;
import com.vmi.module.model.ItemGroup;
import com.vmi.module.model.ItemModel;
import com.vmi.module.model.UomMaster;
import com.vmi.module.repo.ItemCategoryRepo;
import com.vmi.module.repo.ItemGroupRepo;
import com.vmi.module.repo.ItemModelRepo;
import com.vmi.module.repo.UomRepo;
import com.vmi.module.util.CustomErr;
import com.vmi.module.util.Error;
import com.vmi.module.util.ValidationException;

@Transactional
@Service("ItemsGroupsService")
@SuppressWarnings({"unchecked","rawtypes"})
public class ItemsGroupServmpl
  implements ItemsGroupsService
{
  @Autowired
  private ItemGroupRepo itemGroupRepo;
  @Autowired
  private ItemCategoryRepo itemCategoryRepo;
  @Autowired
  private ItemModelRepo itemModelRepo;
  @Autowired
  private UomRepo uomRepo;
  ObjectMapper objMapper = new ObjectMapper();
  private static final Logger LOGGER = LoggerFactory.getLogger(ItemsGroupServmpl.class);
  
  public Map<String, Object> saveDetails(ItemGroup itemGroupreqObj, HttpSession paramHttpSession)
  {
    Map<String, Object> responseHashMap = new HashMap();
    List<Error> errorDetail = new ArrayList();
    try
    {
      this.objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      ItemGroup itemGroupObj = null;
      if (itemGroupreqObj.getId() != null) {
        itemGroupObj = this.itemGroupRepo.findById(itemGroupreqObj.getId());
      } else {
        itemGroupObj = null;
      }
      if (itemGroupObj != null)
      {
        itemGroupreqObj.setCreateDate(itemGroupObj.getCreateDate());
        itemGroupreqObj.setCreateBy(itemGroupObj.getCreateBy());
        itemGroupObj = (ItemGroup)this.objMapper.convertValue(itemGroupreqObj, ItemGroup.class);
        responseHashMap.put("message", "data updated !!");
      }
      else
      {
        itemGroupreqObj.setId(null);
        itemGroupObj = (ItemGroup)this.objMapper.convertValue(itemGroupreqObj, ItemGroup.class);
        responseHashMap.put("message", "data inserted !!");
      }
      itemGroupObj = (ItemGroup)this.itemGroupRepo.save(itemGroupObj);
      responseHashMap.put("itemgroup", itemGroupObj);
    }
    catch (DuplicateKeyException e)
    {
      errorDetail.add(new Error("C005", "Duplicate of ItemGroup  can not be allowed"));
      throw new ValidationException(errorDetail);
    }
    catch (Exception e)
    {
      LOGGER.error("Provider : ItemsGroupServiceImpl /n savegroupdetails : {} /n Exception : ", new Object[] { e });
    }
    return responseHashMap;
  }
  
  public Map<String, Object> getById(String id)
  {
    Map<String, Object> responseDataHashMap = new HashMap();
    ItemGroup itemGroupObj = this.itemGroupRepo.findById(id.trim());
    if (itemGroupObj != null) {
      responseDataHashMap.put("itemGroup", itemGroupObj);
    } else {
      responseDataHashMap.put("message", "No data found");
    }
    return responseDataHashMap;
  }
  
  public Map<String, Object> deleteByItemGrpId(String id)
  {
    Map<String, Object> responseHashMap = new HashMap();
    ItemGroup itemGroupObj = this.itemGroupRepo.findById(id.trim());
    if (itemGroupObj != null)
    {
      this.itemGroupRepo.delete(itemGroupObj);
      responseHashMap.put("message", "data deleted");
    }
    else
    {
      responseHashMap.put("message", "No data found");
    }
    return responseHashMap;
  }
  
  public Map<String, Object> saveItemCategoryDetails(ItemCategory reqObj, HttpSession paramHttpSession)
  {
    Map<String, Object> responseHashMap = new HashMap();
    List<Error> errorDetail = new ArrayList();
    try
    {
      this.objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      ItemCategory itemCategoryObj = this.itemCategoryRepo.findById(reqObj.getId());
      if (itemCategoryObj != null)
      {
        itemCategoryObj.setCreateDate(itemCategoryObj.getCreateDate());
        itemCategoryObj.setCreateBy(itemCategoryObj.getCreateBy());
        itemCategoryObj = (ItemCategory)this.objMapper.convertValue(reqObj, ItemCategory.class);
        responseHashMap.put("message", "data updated !!");
      }
      else
      {
        reqObj.setId(null);
        itemCategoryObj = (ItemCategory)this.objMapper.convertValue(reqObj, ItemCategory.class);
        responseHashMap.put("message", "data inserted !!");
      }
      itemCategoryObj = (ItemCategory)this.itemCategoryRepo.save(itemCategoryObj);
      responseHashMap.put("itemcategory", itemCategoryObj);
    }
    catch (DuplicateKeyException e)
    {
      errorDetail.add(new Error("C005", "Duplicate of ItemCategory  can not be allowed"));
      throw new ValidationException(errorDetail);
    }
    catch (Exception e)
    {
      LOGGER.error("Provider : ItemsGroupServmpl /n saveItemCategorydetails : {} /n Exception : ", new Object[] { e });
      responseHashMap.put("errorData", new CustomErr("LP - 10003", "Request has invalid data,Please try again"));
    }
    return responseHashMap;
  }
  
  public Map<String, Object> getByItemCategoryId(String id)
  {
    Map<String, Object> responseDataHashMap = new HashMap();
    ItemCategory itemCategoryObj = this.itemCategoryRepo.findById(id.trim());
    if (itemCategoryObj != null) {
      responseDataHashMap.put("itemcategory", itemCategoryObj);
    } else {
      responseDataHashMap.put("message", "No data found");
    }
    return responseDataHashMap;
  }
  
  public Map<String, Object> deleteByItemCategoryId(String id)
  {
    Map<String, Object> responseHashMap = new HashMap();
    ItemCategory itemCategoryObj = this.itemCategoryRepo.findById(id.trim());
    if (itemCategoryObj != null)
    {
      this.itemCategoryRepo.delete(itemCategoryObj);
      responseHashMap.put("message", "data deleted");
    }
    else
    {
      responseHashMap.put("message", "No data found");
    }
    return responseHashMap;
  }
  
  public Map<String, Object> saveItemModelDetails(ItemModel reqObj, HttpSession paramHttpSession)
  {
    Map<String, Object> responseHashMap = new HashMap();
    List<Error> errorDetail = new ArrayList();
    try
    {
      this.objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      ItemModel itemModelObj = this.itemModelRepo.findById(reqObj.getId());
      if (itemModelObj != null)
      {
        reqObj.setCreateDate(itemModelObj.getCreateDate());
        reqObj.setCreateBy(itemModelObj.getCreateBy());
        itemModelObj = (ItemModel)this.objMapper.convertValue(reqObj, ItemModel.class);
        responseHashMap.put("message", "data updated !!");
      }
      else
      {
        reqObj.setId(null);
        itemModelObj = (ItemModel)this.objMapper.convertValue(reqObj, ItemModel.class);
        responseHashMap.put("message", "data inserted !!");
      }
      itemModelObj = (ItemModel)this.itemModelRepo.save(itemModelObj);
      responseHashMap.put("itemModeldetails", itemModelObj);
    }
    catch (DuplicateKeyException e)
    {
      errorDetail.add(new Error("C005", "Duplicate of ItemModel  can not be allowed"));
      throw new ValidationException(errorDetail);
    }
    catch (Exception e)
    {
      LOGGER.error("Provider : ItemsGroupServmpl /n saveItemModeldetails : {} /n Exception : ", new Object[] { e });
      responseHashMap.put("errorData", new CustomErr("LP - 10003", "Request has invalid data,Please try again"));
    }
    return responseHashMap;
  }
  
  public Map<String, Object> getByItemModelId(String id)
  {
    Map<String, Object> responseDataHashMap = new HashMap();
    ItemModel itemModelObj = this.itemModelRepo.findById(id.trim());
    if (itemModelObj != null) {
      responseDataHashMap.put("itemModeldetails", itemModelObj);
    } else {
      responseDataHashMap.put("message", "No data found");
    }
    return responseDataHashMap;
  }
  
  public Map<String, Object> deleteByItemModelId(String id)
  {
    Map<String, Object> responseHashMap = new HashMap();
    ItemModel itemModelObj = this.itemModelRepo.findById(id.trim());
    if (itemModelObj != null)
    {
      this.itemModelRepo.delete(itemModelObj);
      responseHashMap.put("message", "data deleted");
    }
    else
    {
      responseHashMap.put("message", "No data found");
    }
    return responseHashMap;
  }
  
  public Map<String, Object> saveUomDetails(UomMaster reqObj, HttpSession paramHttpSession)
  {
    Map<String, Object> responseHashMap = new HashMap();
    List<Error> errorDetail = new ArrayList();
    try
    {
      this.objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      UomMaster uomMasterObj = this.uomRepo.findByIdOrderByCreateDateDesc(reqObj.getId());
      if (uomMasterObj != null)
      {
        reqObj.setCreateDate(uomMasterObj.getCreateDate());
        reqObj.setCreateBy(uomMasterObj.getCreateBy());
        uomMasterObj = (UomMaster)this.objMapper.convertValue(reqObj, UomMaster.class);
        responseHashMap.put("message", "data updated !!");
      }
      else
      {
        reqObj.setId(null);
        uomMasterObj = (UomMaster)this.objMapper.convertValue(reqObj, UomMaster.class);
        responseHashMap.put("message", "data inserted !!");
      }
      uomMasterObj = (UomMaster)this.uomRepo.save(uomMasterObj);
      responseHashMap.put("uomdetails", uomMasterObj);
    }
    catch (DuplicateKeyException e)
    {
      errorDetail.add(new Error("C005", "Duplicate of Uom can not be allowed"));
      throw new ValidationException(errorDetail);
    }
    catch (Exception e)
    {
      LOGGER.error("Provider : ItemsGroupServmpl /n saveuomMasterdetails : {} /n Exception : ", new Object[] { e });
      
      responseHashMap.put("errorData", new CustomErr("LP - 10003", "Request has invalid data,Please try again"));
    }
    return responseHashMap;
  }
  
  public Map<String, Object> getByUomId(String id)
  {
    Map<String, Object> responseDataHashMap = new HashMap();
    UomMaster uomMasterObj = this.uomRepo.findByIdOrderByCreateDateDesc(id.trim());
    if (uomMasterObj != null) {
      responseDataHashMap.put("uomMaster", uomMasterObj);
    } else {
      responseDataHashMap.put("message", "No data found");
    }
    return responseDataHashMap;
  }
  
  public Map<String, Object> deleteByUomId(String id)
  {
    Map<String, Object> responseHashMap = new HashMap();
    UomMaster uomMasterObj = this.uomRepo.findByIdOrderByCreateDateDesc(id.trim());
    if (uomMasterObj != null)
    {
      this.uomRepo.delete(uomMasterObj);
      responseHashMap.put("message", "data deleted");
    }
    else
    {
      responseHashMap.put("message", "No data found");
    }
    return responseHashMap;
  }
  
  public Map<String, Object> getListItemGroup()
  {
    Map<String, Object> responseDataHashMap = new HashMap();
    List<ItemGroup> itemGroupObj = this.itemGroupRepo.findAll();
    if (itemGroupObj != null) {
      responseDataHashMap.put("itemGroup", itemGroupObj);
    } else {
      responseDataHashMap.put("message", "No data found");
    }
    return responseDataHashMap;
  }
  
  public Map<String, Object> getListItemCategory()
  {
    Map<String, Object> responseDataHashMap = new HashMap();
    List<ItemCategory> itemCategoryObj = this.itemCategoryRepo.findAll();
    if (itemCategoryObj != null) {
      responseDataHashMap.put("itemCategory", itemCategoryObj);
    } else {
      responseDataHashMap.put("message", "No data found");
    }
    return responseDataHashMap;
  }
  
  public Map<String, Object> getListItemModel()
  {
    Map<String, Object> responseDataHashMap = new HashMap();
    List<ItemModel> itemModelObj = this.itemModelRepo.findAll();
    if (itemModelObj != null) {
      responseDataHashMap.put("itemModel", itemModelObj);
    } else {
      responseDataHashMap.put("message", "No data found");
    }
    return responseDataHashMap;
  }
  
  public Map<String, Object> getListUom()
  {
    Map<String, Object> responseDataHashMap = new HashMap();
    List<UomMaster> uomMasterObj = this.uomRepo.findAllByOrderByCreateDateDesc();
    if (uomMasterObj != null) {
      responseDataHashMap.put("itemUom", uomMasterObj);
    } else {
      responseDataHashMap.put("message", "No data found");
    }
    return responseDataHashMap;
  }
  
  public Map<String, Object> getItemGroupCategory(String id)
  {
    Map<String, Object> responseDataHashMap = new HashMap();
    List<ItemCategory> listitemCategory = this.itemCategoryRepo.findByItemgroup(id.trim());
    if (listitemCategory != null) {
      responseDataHashMap.put("itemCategory", listitemCategory);
    } else {
      responseDataHashMap.put("message", "No data found");
    }
    return responseDataHashMap;
  }
  
  public Map<String, Object> getByItemCategoryModel(String id)
  {
    Map<String, Object> responseDataHashMap = new HashMap();
    List<ItemModel> itemModelObj = this.itemModelRepo.findByItemCategory(id.trim());
    if (itemModelObj != null) {
      responseDataHashMap.put("itemModel", itemModelObj);
    } else {
      responseDataHashMap.put("message", "No data found");
    }
    return responseDataHashMap;
  }
  
  public Map<String, Object> getByItemModelUomList(String id)
  {
    Map<String, Object> responseDataHashMap = new HashMap();
    List<UomMaster> uomObj = this.uomRepo.findByItemModel(id.trim());
    if (uomObj != null) {
      responseDataHashMap.put("itemUom", uomObj);
    } else {
      responseDataHashMap.put("message", "No data found");
    }
    return responseDataHashMap;
  }
}
