package com.vmi.module.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="WAREHOUSE_MAPPING")
@JsonIgnoreProperties({"createDate", "createBy", "modifiedDate", "modifiedBy"})
public class WareHouseMapping
{
  @Id
  private String id;
  @DBRef
  @Field("ITEM_MASTER")
  private ItemMaster itemMaster;
  @DBRef
  @Field("WAREHOUSE_DETAILS")
  private WareHouseDetails wareHouse;
  private int supplyLead;
  private long greenZone;
  private long yellowZone;
  private long redZone;
  private long currentStock;
  private long itemMax;
  @CreatedDate
  @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
  private Date createDate;
  @CreatedBy
  private String createBy;
  @LastModifiedDate
  @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
  private Date modifiedDate;
  @LastModifiedBy
  private String modifiedBy;
  
  public String getId()
  {
    return this.id;
  }
  
  public void setId(String id)
  {
    this.id = id;
  }
  
  public ItemMaster getItemMaster()
  {
    return this.itemMaster;
  }
  
  public void setItemMaster(ItemMaster itemMaster)
  {
    this.itemMaster = itemMaster;
  }
  
  public int getSupplyLead()
  {
    return this.supplyLead;
  }
  
  public void setSupplyLead(int supplyLead)
  {
    this.supplyLead = supplyLead;
  }
  
  public long getGreenZone()
  {
    return this.greenZone;
  }
  
  public void setGreenZone(long greenZone)
  {
    this.greenZone = greenZone;
  }
  
  public long getYellowZone()
  {
    return this.yellowZone;
  }
  
  public void setYellowZone(long yellowZone)
  {
    this.yellowZone = yellowZone;
  }
  
  public long getRedZone()
  {
    return this.redZone;
  }
  
  public void setRedZone(long redZone)
  {
    this.redZone = redZone;
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
  
  public Date getModifiedDate()
  {
    return this.modifiedDate;
  }
  
  public void setModifiedDate(Date modifiedDate)
  {
    this.modifiedDate = modifiedDate;
  }
  
  public String getModifiedBy()
  {
    return this.modifiedBy;
  }
  
  public void setModifiedBy(String modifiedBy)
  {
    this.modifiedBy = modifiedBy;
  }
  
  public WareHouseDetails getWareHouse()
  {
    return this.wareHouse;
  }
  
  public void setWareHouse(WareHouseDetails wareHouse)
  {
    this.wareHouse = wareHouse;
  }
  
  public long getCurrentStock()
  {
    return this.currentStock;
  }
  
  public void setCurrentStock(long currentStock)
  {
    this.currentStock = currentStock;
  }
  
  public long getItemMax()
  {
    return this.itemMax;
  }
  
  public void setItemMax(long itemMax)
  {
    this.itemMax = itemMax;
  }
}
