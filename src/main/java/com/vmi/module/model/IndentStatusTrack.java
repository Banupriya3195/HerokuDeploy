package com.vmi.module.model;

import java.util.Date;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

@Document(collection="INDENTS_STATUSTRACK")
public class IndentStatusTrack
{
  @Id
  private String id;
  private String indentId;
  private long indentShipQuantity;
  private long indentRecQuantity;
  @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
  private Date indentShipDate;
  @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
  private Date indentRecDate;
  private String remarks;
  @CreatedDate
  @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
  private Date createDate;
  @CreatedBy
  private String createUser;
  @LastModifiedDate
  @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
  private Date modifiedDate;
  @LastModifiedBy
  private String modifiedUser;
  
  public String getId()
  {
    return this.id;
  }
  
  public void setId(String id)
  {
    this.id = id;
  }
  
  public long getIndentShipQuantity()
  {
    return this.indentShipQuantity;
  }
  
  public void setIndentShipQuantity(long indentShipQuantity)
  {
    this.indentShipQuantity = indentShipQuantity;
  }
  
  public long getIndentRecQuantity()
  {
    return this.indentRecQuantity;
  }
  
  public void setIndentRecQuantity(long indentRecQuantity)
  {
    this.indentRecQuantity = indentRecQuantity;
  }
  
  public Date getIndentShipDate()
  {
    return this.indentShipDate;
  }
  
  public void setIndentShipDate(Date indentShipDate)
  {
    this.indentShipDate = indentShipDate;
  }
  
  public Date getIndentRecDate()
  {
    return this.indentRecDate;
  }
  
  public void setIndentRecDate(Date indentRecDate)
  {
    this.indentRecDate = indentRecDate;
  }
  
  public Date getCreateDate()
  {
    return this.createDate;
  }
  
  public void setCreateDate(Date createDate)
  {
    this.createDate = createDate;
  }
  
  public String getCreateUser()
  {
    return this.createUser;
  }
  
  public void setCreateUser(String createUser)
  {
    this.createUser = createUser;
  }
  
  public Date getModifiedDate()
  {
    return this.modifiedDate;
  }
  
  public void setModifiedDate(Date modifiedDate)
  {
    this.modifiedDate = modifiedDate;
  }
  
  public String getModifiedUser()
  {
    return this.modifiedUser;
  }
  
  public void setModifiedUser(String modifiedUser)
  {
    this.modifiedUser = modifiedUser;
  }
  
  public String getRemarks()
  {
    return this.remarks;
  }
  
  public void setRemarks(String remarks)
  {
    this.remarks = remarks;
  }
  
  public String getIndentId()
  {
    return this.indentId;
  }
  
  public void setIndentId(String indentId)
  {
    this.indentId = indentId;
  }
}
