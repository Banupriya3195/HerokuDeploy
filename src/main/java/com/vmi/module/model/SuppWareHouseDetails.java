package com.vmi.module.model;

public class SuppWareHouseDetails
{
  private String id;
  private String warehouseName;
  private String warehouseCode;
  private String warehouseContact;
  private String warehouseEmail;
  private String warehouseAdress;
  private boolean disabled = false;
  
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
  
  public String getWarehouseContact()
  {
    return this.warehouseContact;
  }
  
  public void setWarehouseContact(String warehouseContact)
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
  
  public boolean isDisabled()
  {
    return this.disabled;
  }
  
  public void setDisabled(boolean disabled)
  {
    this.disabled = disabled;
  }
}
