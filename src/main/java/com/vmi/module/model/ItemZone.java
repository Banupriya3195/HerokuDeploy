package com.vmi.module.model;

public class ItemZone
{
  private long itemvalue;
  private String itemcolour;
  
  public String getItemcolour()
  {
    return this.itemcolour;
  }
  
  public void setItemcolour(String itemcolour)
  {
    this.itemcolour = itemcolour;
  }
  
  public long getItemvalue()
  {
    return this.itemvalue;
  }
  
  public void setItemvalue(long itemvalue)
  {
    this.itemvalue = itemvalue;
  }
  
  public ItemZone(long itemvalue, String itemcolour)
  {
    this.itemvalue = itemvalue;
    this.itemcolour = itemcolour;
  }
}
