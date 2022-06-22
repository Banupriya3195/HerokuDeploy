package com.vmi.module.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import java.util.List;
import javax.persistence.Transient;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="INDENT_DETAILS")
@JsonIgnoreProperties({"createDate", "createBy", "modifiedDate", "modifiedBy"})
public class IndentDetails
{
  @Id
  private String id;
  private String indentPriority;
  private long indentNo;
  @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
  private Date indentDate;
  @DBRef
  @Field("ITEM_MASTER")
  private ItemMaster itemMaster;
  @DBRef
  @Field("COMPANY_DETAILS")
  private CompanyProfile company;
  private long indentQuantity;
  @DBRef
  @Field("WAREHOUSE_DETAILS")
  private WareHouseDetails wareHouse;
  @DBRef
  @Field("INDENT_STATUS")
  private IndentStatus indentStatus;
  @Transient
  private List<IndentStatusTrack> indentsTrack;
  @Transient
  public static final String SEQUENCE_NAME = "indents_sequence";
  @CreatedDate
  @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
  private Date createDate;
  @CreatedBy
  private String createUser;
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
  
  public String getIndentPriority()
  {
    return this.indentPriority;
  }
  
  public void setIndentPriority(String indentPriority)
  {
    this.indentPriority = indentPriority;
  }
  
  public ItemMaster getItemMaster()
  {
    return this.itemMaster;
  }
  
  public void setItemMaster(ItemMaster itemMaster)
  {
    this.itemMaster = itemMaster;
  }
  
  public CompanyProfile getCompany()
  {
    return this.company;
  }
  
  public void setCompany(CompanyProfile company)
  {
    this.company = company;
  }
  
  public long getIndentQuantity()
  {
    return this.indentQuantity;
  }
  
  public void setIndentQuantity(long indentQuantity)
  {
    this.indentQuantity = indentQuantity;
  }
  
  public Date getCreateDate()
  {
    return this.createDate;
  }
  
  public void setCreateDate(Date createDate)
  {
    this.createDate = createDate;
  }
  
  public Date getModifiedDate()
  {
    return this.modifiedDate;
  }
  
  public void setModifiedDate(Date modifiedDate)
  {
    this.modifiedDate = modifiedDate;
  }
  
  public WareHouseDetails getWareHouse()
  {
    return this.wareHouse;
  }
  
  public void setWareHouse(WareHouseDetails wareHouse)
  {
    this.wareHouse = wareHouse;
  }
  
  public String getModifiedBy()
  {
    return this.modifiedBy;
  }
  
  public void setModifiedBy(String modifiedBy)
  {
    this.modifiedBy = modifiedBy;
  }
  
  public long getIndentNo()
  {
    return this.indentNo;
  }
  
  public void setIndentNo(long indentNo)
  {
    this.indentNo = indentNo;
  }
  
  public IndentStatus getIndentStatus()
  {
    return this.indentStatus;
  }
  
  public void setIndentStatus(IndentStatus indentStatus)
  {
    this.indentStatus = indentStatus;
  }
  
  public String getCreateUser()
  {
    return this.createUser;
  }
  
  public void setCreateUser(String createUser)
  {
    this.createUser = createUser;
  }
  
  public List<IndentStatusTrack> getIndentsTrack()
  {
    return this.indentsTrack;
  }
  
  public void setIndentsTrack(List<IndentStatusTrack> indentsTrack)
  {
    this.indentsTrack = indentsTrack;
  }
  
  public Date getIndentDate()
  {
    return this.indentDate;
  }
  
  public void setIndentDate(Date indentDate)
  {
    this.indentDate = indentDate;
  }
}
