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
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="ITEM_MODEL")
@JsonIgnoreProperties({"createDate", "createBy", "modifiedDate", "modifiedBy"})
public class ItemModel
{
  @Id
  private String id;
  @Indexed(unique=true)
  private String itemModel;
  private boolean disabled = false;
  @DBRef
  @Field("ITEM_CATEGORY")
  private ItemCategory itemCategory;
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
  
  public String getItemModel()
  {
    return this.itemModel;
  }
  
  public void setItemModel(String itemModel)
  {
    this.itemModel = itemModel;
  }
  
  public ItemCategory getItemCategory()
  {
    return this.itemCategory;
  }
  
  public void setItemCategory(ItemCategory itemCategory)
  {
    this.itemCategory = itemCategory;
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
  
  public boolean isDisabled()
  {
    return this.disabled;
  }
  
  public void setDisabled(boolean disabled)
  {
    this.disabled = disabled;
  }
}
