package com.vmi.module.service;

import com.vmi.module.model.IndentDetails;
import com.vmi.module.model.IndentStatus;
import com.vmi.module.model.Users;
import java.text.ParseException;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.web.multipart.MultipartFile;

public abstract interface IndentService
{
  public abstract Map<String, Object> saveDetails(IndentDetails paramIndentDetails, HttpSession paramHttpSession);
  
  public abstract Map<String, Object> deleteById(String paramString);
  
  public abstract Map<String, Object> getListIndents(Users paramUsers, String paramString);
  
  public abstract Map<String, Object> getIndentFile(MultipartFile paramMultipartFile)
    throws ParseException;
  
  public abstract Map<String, Object> getColourListIndents(String paramString1, String paramString2);
  
  public abstract Map<String, Object> getDateRangeListIndents(String paramString1, String paramString2, String paramString3);
  
  public abstract Map<String, Object> saveDetails(IndentStatus paramIndentStatus, HttpSession paramHttpSession);
  
  public abstract Map<String, Object> getIndentStatus();
  
  public abstract Map<String, Object> getLatestListIndents(Users paramUsers, String paramString);
  
  public abstract Map<String, Object> getListIndentsTrack(String paramString);
}
