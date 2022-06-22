package com.vmi.module.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import javax.persistence.Transient;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="APPLICATION_PROPERTIES")
@JsonIgnoreProperties({"createDate", "createBy", "modifiedDate", "modifiedBy"})
public class Applicationproperties
{
  @Id
  private long valId;
  private String propertyname;
  private String propertyValue;
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
  public static final String SEQUENCE_NAME = "Property_sequence";
  
  public String getPropertyname()
  {
    return this.propertyname;
  }
  
  public void setPropertyname(String propertyname)
  {
    this.propertyname = propertyname;
  }
  
  public String getPropertyValue()
  {
    return this.propertyValue;
  }
  
  public void setPropertyValue(String propertyValue)
  {
    this.propertyValue = propertyValue;
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
  
  public long getValId()
  {
    return this.valId;
  }
  
  public void setValId(long valId)
  {
    this.valId = valId;
  }
}
