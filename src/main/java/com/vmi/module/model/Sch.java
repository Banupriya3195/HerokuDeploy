package com.vmi.module.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="SUPPLYCHAIN_COMDETAILS")
@JsonIgnoreProperties({"createDate", "createBy", "modifiedDate", "modifiedBy"})
public class Sch
{
  @Id
  private String id;
  private String parentWarehouseId;
  @DBRef
  @Field("WAREHOUSE_DETAILS")
  private WareHouseDetails wareHouse;
  @DBRef
  @Field("COMPANY_DETAILS")
  private CompanyProfile company;
  @CreatedDate
  @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
  private Date createDate;
  @CreatedBy
  private String createBy;
  @LastModifiedDate
  @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
  @Temporal(TemporalType.DATE)
  private Date modifiedDate;
  @LastModifiedBy
  private String modifiedBy;
  
  public String getId()
  {
    return this.id;
  }
  
  public String getParentWarehouseId()
  {
    return this.parentWarehouseId;
  }
  
  public void setParentWarehouseId(String parentWarehouseId)
  {
    this.parentWarehouseId = parentWarehouseId;
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
  
  public void setId(String id)
  {
    this.id = id;
  }
  
  public CompanyProfile getCompany()
  {
    return this.company;
  }
  
  public void setCompany(CompanyProfile company)
  {
    this.company = company;
  }
  
  public WareHouseDetails getWareHouse()
  {
    return this.wareHouse;
  }
  
  public void setWareHouse(WareHouseDetails wareHouse)
  {
    this.wareHouse = wareHouse;
  }
}
