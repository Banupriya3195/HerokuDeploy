package com.vmi.module.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vmi.module.model.Applicationproperties;
import com.vmi.module.model.ItemMaster;
import com.vmi.module.model.UomMaster;
import com.vmi.module.repo.ApplicationPropertiesRepo;
import com.vmi.module.repo.ItemMasterRepo;
import com.vmi.module.util.Error;
import com.vmi.module.util.ValidationException;
import com.vmi.module.validations.Helper;






@Transactional
@Service("ItemMasterService")
public class ItemMasterServiceImpl
  implements ItemMasterService
{
  @Autowired
  private ItemMasterRepo itemMasterRepo;
  @Autowired
  private ApplicationPropertiesRepo applicationPropertiesRepo;
  ObjectMapper objMapper = new ObjectMapper();
  private static final Logger LOGGER = LoggerFactory.getLogger(ItemMasterServiceImpl.class);
  
  public ItemMasterServiceImpl() {}
  
  public Map<String, Object> saveDetails(ItemMaster reqObj) {
    Map<String, Object> responseHashMap = new HashMap();
    List<Error> errorDetail = new ArrayList();
    try
    {
      objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      ItemMaster itemMaster = itemMasterRepo.findById(reqObj.getId());
      if (itemMaster != null) {
        reqObj.setCreateDate(itemMaster.getCreateDate());
        reqObj.setCreateBy(itemMaster.getCreateBy());
        itemMaster = (ItemMaster)objMapper.convertValue(reqObj, ItemMaster.class);
        responseHashMap.put("message", "data updated !!");
      } else {
        reqObj.setId(null);
        itemMaster = (ItemMaster)objMapper.convertValue(reqObj, ItemMaster.class);
        responseHashMap.put("message", "data inserted !!");
      }
      itemMaster = (ItemMaster)itemMasterRepo.save(itemMaster);
      responseHashMap.put("itemMaster", itemMaster);
    }
    catch (DuplicateKeyException e)
    {
      errorDetail.add(new Error("C006", "Duplicate values of Item Name,Code,Description can not be allowed"));
      throw new ValidationException(errorDetail);
    }
    catch (Exception e) {
    	LOGGER.error("Provider : ItemMasterServiceImpl /n saveitemdetail : {} /n Exception : ", new Object[] { e });
    }
    return responseHashMap;
  }
  
  public Map<String, Object> getById(String id)
  {
    Map<String, Object> responseDataHashMap = new HashMap();
    ItemMaster itemMaster = itemMasterRepo.findById(id.trim());
    if (itemMaster != null) {
      responseDataHashMap.put("itemMaster", itemMaster);
    } else {
      responseDataHashMap.put("message", "No data found");
    }
    return responseDataHashMap;
  }
  
  public String fileToArray(String filename, String IMAGE_BASE_DIR)
  {
    String outArray = null;
    File file = new File(IMAGE_BASE_DIR + filename);
    ByteArrayOutputStream out = null;
    try
    {
      InputStream in = new FileInputStream(file);
      out = new ByteArrayOutputStream();
      int data = in.read();
      while (data >= 0) {
        out.write((char)data);
        data = in.read();
      }
      out.flush();
      outArray = new String(out.toByteArray());
    }
    catch (Exception localException) {}
    
    return outArray;
  }
  

  public Map<String, Object> deleteItemMasterById(String id)
  {
    Map<String, Object> responseHashMap = new HashMap();
    ItemMaster itemMaster = itemMasterRepo.findById(id.trim());
    if (itemMaster != null) {
      File file = new File(itemMaster.getItemImagePath());
      if (file.exists())
        file.delete();
      itemMasterRepo.delete(itemMaster);
      
      responseHashMap.put("message", "data deleted");
    } else {
      responseHashMap.put("message", "No data found");
    }
    return responseHashMap;
  }
  
  public Map<String, Object> getListItemmaster()
  {
    Map<String, Object> responseDataHashMap = new HashMap();
    List<ItemMaster> itemMaster = itemMasterRepo.findAllByOrderByCreateDateDesc();
    if (itemMaster != null) {
      responseDataHashMap.put("itemMaster", itemMaster);
    } else {
      responseDataHashMap.put("message", "No data found");
    }
    return responseDataHashMap;
  }
  

  public Map<String, Object> deleteById(String id)
  {
    Map<String, Object> responseHashMap = new HashMap();
    ItemMaster itemmaster = itemMasterRepo.findById(id.trim());
    if (itemmaster != null) {
      itemMasterRepo.delete(itemmaster);
      responseHashMap.put("message", "data deleted");
    } else {
      responseHashMap.put("message", "No data found");
    }
    return responseHashMap;
  }
  


  public Map<String, Object> saveItemsBulUpload(MultipartFile file, String uomId)
  {
    Map<String, Object> responseHashMap = new HashMap();
    try {
      InputStream inStream = file.getInputStream();
      
      Workbook wb = WorkbookFactory.create(inStream);
      Sheet sheet = wb.getSheetAt(0);
      List<ItemMaster> listItemMaster = new ArrayList();
      UomMaster uomObj = new UomMaster();
      uomObj.setId(uomId.trim());
      ItemMaster itemMaster = null;
      for (Row row : sheet) {
        if (row.getRowNum() != 0) {
          itemMaster = new ItemMaster();
          if ((row.getCell(0) != null) && (row.getCell(0).toString() != "") && (row.getCell(1) != null) && 
            (row.getCell(1).toString() != "") && (row.getCell(2) != null) && 
            (row.getCell(2).toString() != "") && (row.getCell(3) != null) && 
            (row.getCell(3).toString() != "") && (row.getCell(4) != null) && 
            (row.getCell(4).toString() != "") && (row.getCell(5) != null) && 
            (row.getCell(5).toString() != "") && (itemMasterRepo.findByItemNameAndItemCodeAndItemDescription(row.getCell(0).toString().trim(), row.getCell(1).toString().trim(), row.getCell(2).toString().trim()) == null)) {
            itemMaster.setItemName(row.getCell(0).toString().trim());
            itemMaster.setItemCode(row.getCell(1).toString().trim());
            itemMaster.setItemDescription(row.getCell(2).toString().trim());
            itemMaster.setItemRate(Double.valueOf(row.getCell(3).toString().trim()));
            
            itemMaster.setItemImageName(row.getCell(5).toString().trim());
            itemMaster.setItemUom(uomObj);
            listItemMaster.add(itemMaster);
          }
        }
      }
      listItemMaster = itemMasterRepo.save(listItemMaster);
      responseHashMap.put("itemMaster", itemMaster);
    }
    catch (EncryptedDocumentException|InvalidFormatException|IOException e) {
      List<Error> errorDetail = new ArrayList();
      errorDetail.add(new Error("C003", e.getMessage()));
      throw new ValidationException(errorDetail);
    }
    return responseHashMap;
  }
  
  public Map<String, Object> test(MultipartFile file)
  {
    try {
      String IMAGE_BASE_DIR = "C:/Users/kishore.bellamkonda/Desktop/balu/";
      String fileName = file.getOriginalFilename();
      File filek = new File(".");
      System.out.println("ddd" + filek.getAbsolutePath());
      
      String bb = file.getContentType();
      if ("image/jpeg".equals(bb)) {}
      

      InputStream is = file.getInputStream();
      Files.copy(is, Paths.get(IMAGE_BASE_DIR + fileName, new String[0]), new CopyOption[] { StandardCopyOption.REPLACE_EXISTING });
    }
    catch (IOException localIOException) {}
    



    return null;
  }
  
  public Map<String, Object> updateImage(MultipartFile file, String itemMasterId)
  {
    List<Error> errorDetail = new ArrayList();
    if ((!"image/jpeg".equals(file.getContentType())) && (!"image/png".equals(file.getContentType()))) {
      throw new ValidationException(errorDetail);
    }
    Applicationproperties properties = applicationPropertiesRepo.findByValId(1L);
    String IMAGE_BASE_DIR = properties.getPropertyValue();
    Map<String, Object> responseHashMap = new HashMap();
    try {
      ItemMaster itemMaster = itemMasterRepo.findById(itemMasterId.trim());
      File filePath = new File(IMAGE_BASE_DIR);
      if (!filePath.exists()) {
        filePath.mkdir();
      }
      String fileName = file.getOriginalFilename();
      if (fileName != null) {
        fileName = fileName.split("\\.")[0] + Helper.getDateTime() + "." + fileName.split("\\.")[1];
      }
      InputStream is = file.getInputStream();
      itemMaster.setItemImageName(fileName);
      itemMaster.setItemImagePath(IMAGE_BASE_DIR + fileName);
      Files.copy(is, Paths.get(IMAGE_BASE_DIR + fileName, new String[0]), new CopyOption[] { StandardCopyOption.REPLACE_EXISTING });
      itemMaster = (ItemMaster)itemMasterRepo.save(itemMaster);
      responseHashMap.put("itemMaster", itemMaster);
    }
    catch (ValidationException e) {
      errorDetail.add(new Error("C003", "Invali file format"));
      throw new ValidationException(errorDetail);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return responseHashMap;
  }
}