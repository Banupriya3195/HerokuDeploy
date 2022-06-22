package com.vmi.module.service;

import com.vmi.module.model.ItemMaster;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

public abstract interface ItemMasterService
{
  public abstract Map<String, Object> saveDetails(ItemMaster paramItemMaster);
  
  public abstract Map<String, Object> getById(String paramString);
  
  public abstract Map<String, Object> deleteItemMasterById(String paramString);
  
  public abstract Map<String, Object> getListItemmaster();
  
  public abstract Map<String, Object> deleteById(String paramString);
  
  public abstract Map<String, Object> saveItemsBulUpload(MultipartFile paramMultipartFile, String paramString);
  
  public abstract Map<String, Object> test(MultipartFile paramMultipartFile);
  
  public abstract Map<String, Object> updateImage(MultipartFile paramMultipartFile, String paramString);
}
