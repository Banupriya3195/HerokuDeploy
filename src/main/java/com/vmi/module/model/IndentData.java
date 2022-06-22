package com.vmi.module.model;

public class IndentData
{
  private String companyCode;
  private String warehouseCode;
  private String itemCode;
  private long quantityConsumed;
  private String indentDate;
  
  public String getCompanyCode()
  {
    return this.companyCode;
  }
  
  public void setCompanyCode(String companyCode)
  {
    this.companyCode = companyCode;
  }
  
  public String getWarehouseCode()
  {
    return this.warehouseCode;
  }
  
  public void setWarehouseCode(String warehouseCode)
  {
    this.warehouseCode = warehouseCode;
  }
  
  public String getItemCode()
  {
    return this.itemCode;
  }
  
  public void setItemCode(String itemCode)
  {
    this.itemCode = itemCode;
  }
  
  public long getQuantityConsumed()
  {
    return this.quantityConsumed;
  }
  
  public void setQuantityConsumed(long quantityConsumed)
  {
    this.quantityConsumed = quantityConsumed;
  }
  
  public String getIndentDate()
  {
    return this.indentDate;
  }
  
  public void setIndentDate(String indentDate)
  {
    this.indentDate = indentDate;
  }
}
