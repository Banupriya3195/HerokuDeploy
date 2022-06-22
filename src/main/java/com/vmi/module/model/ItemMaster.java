package com.vmi.module.model;

import java.util.Date;

import javax.persistence.Transient;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Document(collection="ITEM_MASTER")
@JsonIgnoreProperties({"createDate", "createBy", "modifiedDate", "modifiedBy"})
public class ItemMaster
{
  @Id
  private String id;
  @Indexed(unique=true)
  private String itemName;
  @Indexed(unique=true)
  private String itemCode;
  private String itemDescription;
  @DBRef
  @Field("UOM_MASTER")
  private UomMaster itemUom;
  private Double itemRate;
  private String itemImageName;
  private String itemImagePath;
  private String partNumber;
  private String longDescription;
  private boolean disabled = false;
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
  @Transient
  private String itemZone;
  
  public String getId()
  {
    return this.id;
  }
  
  public void setId(String id)
  {
    this.id = id;
  }
  
  public String getItemName()
  {
    return this.itemName;
  }
  
  public void setItemName(String itemName)
  {
    this.itemName = itemName;
  }
  
  public String getItemCode()
  {
    return this.itemCode;
  }
  
  public void setItemCode(String itemCode)
  {
    this.itemCode = itemCode;
  }
  
  public Double getItemRate()
  {
    return this.itemRate;
  }
  
  public void setItemRate(Double itemRate)
  {
    this.itemRate = itemRate;
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
  
  public UomMaster getItemUom()
  {
    return this.itemUom;
  }
  
  public void setItemUom(UomMaster itemUom)
  {
    this.itemUom = itemUom;
  }
  
  public String getItemDescription()
  {
    return this.itemDescription;
  }
  
  public void setItemDescription(String itemDescription)
  {
    this.itemDescription = itemDescription;
  }
  
  public String getItemImagePath()
  {
    return this.itemImagePath;
  }
  
  public void setItemImagePath(String itemImagePath)
  {
    this.itemImagePath = itemImagePath;
  }
  
  public String getItemImageName()
  {
    return this.itemImageName;
  }
  
  public void setItemImageName(String itemImageName)
  {
    this.itemImageName = itemImageName;
  }
  
  public String getItemZone()
  {
    return this.itemZone;
  }
  
  public void setItemZone(String itemZone)
  {
    this.itemZone = itemZone;
  }
  
  public boolean isDisabled()
  {
    return this.disabled;
  }
  
  public String getPartNumber() {
	return partNumber;
}

public void setPartNumber(String partNumber) {
	this.partNumber = partNumber;
}

public String getLongDescription() {
	return longDescription;
}

public void setLongDescription(String longDescription) {
	this.longDescription = longDescription;
}

public void setDisabled(boolean disabled)
  {
    this.disabled = disabled;
  }
}
