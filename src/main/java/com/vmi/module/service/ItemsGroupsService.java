package com.vmi.module.service;

import com.vmi.module.model.ItemCategory;
import com.vmi.module.model.ItemGroup;
import com.vmi.module.model.ItemModel;
import com.vmi.module.model.UomMaster;
import java.util.Map;
import javax.servlet.http.HttpSession;

public abstract interface ItemsGroupsService
{
  public abstract Map<String, Object> saveDetails(ItemGroup paramItemGroup, HttpSession paramHttpSession);
  
  public abstract Map<String, Object> getById(String paramString);
  
  public abstract Map<String, Object> deleteByItemGrpId(String paramString);
  
  public abstract Map<String, Object> saveItemCategoryDetails(ItemCategory paramItemCategory, HttpSession paramHttpSession);
  
  public abstract Map<String, Object> getByItemCategoryId(String paramString);
  
  public abstract Map<String, Object> deleteByItemCategoryId(String paramString);
  
  public abstract Map<String, Object> saveItemModelDetails(ItemModel paramItemModel, HttpSession paramHttpSession);
  
  public abstract Map<String, Object> getByItemModelId(String paramString);
  
  public abstract Map<String, Object> deleteByItemModelId(String paramString);
  
  public abstract Map<String, Object> saveUomDetails(UomMaster paramUomMaster, HttpSession paramHttpSession);
  
  public abstract Map<String, Object> getByUomId(String paramString);
  
  public abstract Map<String, Object> deleteByUomId(String paramString);
  
  public abstract Map<String, Object> getListItemGroup();
  
  public abstract Map<String, Object> getListItemCategory();
  
  public abstract Map<String, Object> getListItemModel();
  
  public abstract Map<String, Object> getListUom();
  
  public abstract Map<String, Object> getItemGroupCategory(String paramString);
  
  public abstract Map<String, Object> getByItemCategoryModel(String paramString);
  
  public abstract Map<String, Object> getByItemModelUomList(String paramString);
}
