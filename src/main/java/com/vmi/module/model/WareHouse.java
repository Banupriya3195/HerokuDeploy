package com.vmi.module.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import org.springframework.data.annotation.CreatedBy;

@JsonIgnoreProperties({"createDate", "createBy"})
public class WareHouse
{
  private String id;
  private String warehouseName;
  private String warehouseCode;
  private int warehouseContact;
  private String warehouseEmail;
  private String warehouseAdress;
  @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
  private Date createDate;
  @CreatedBy
  private String createBy;
  
  public String getId()
  {
    return this.id;
  }
  
  public void setId(String id)
  {
    this.id = id;
  }
  
  public String getWarehouseName()
  {
    return this.warehouseName;
  }
  
  public void setWarehouseName(String warehouseName)
  {
    this.warehouseName = warehouseName;
  }
  
  public String getWarehouseCode()
  {
    return this.warehouseCode;
  }
  
  public void setWarehouseCode(String warehouseCode)
  {
    this.warehouseCode = warehouseCode;
  }
  
  public int getWarehouseContact()
  {
    return this.warehouseContact;
  }
  
  public void setWarehouseContact(int warehouseContact)
  {
    this.warehouseContact = warehouseContact;
  }
  
  public String getWarehouseEmail()
  {
    return this.warehouseEmail;
  }
  
  public void setWarehouseEmail(String warehouseEmail)
  {
    this.warehouseEmail = warehouseEmail;
  }
  
  public String getWarehouseAdress()
  {
    return this.warehouseAdress;
  }
  
  public void setWarehouseAdress(String warehouseAdress)
  {
    this.warehouseAdress = warehouseAdress;
  }
  
  public Date getCreateDate()
  {
    return this.createDate;
  }
  
  public void setCreateDate(Date createDate)
  {
    this.createDate = createDate;
  }
  
  public String getCreateBy()
  {
    return this.createBy;
  }
  
  public void setCreateBy(String createBy)
  {
    this.createBy = createBy;
  }
}
