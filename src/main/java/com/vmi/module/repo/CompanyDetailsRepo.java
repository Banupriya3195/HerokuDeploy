package com.vmi.module.repo;

import com.vmi.module.model.CompanyProfile;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public abstract interface CompanyDetailsRepo
  extends MongoRepository<CompanyProfile, String>
{
  public abstract CompanyProfile findByName(String paramString);
  
  public abstract CompanyProfile findById(String paramString);
  
  @Query(value="{ 'userId' : ?0, 'wareHouseDetails.warehouseName' : ?1 }", fields="{ 'wareHouseDetails.warehouseName' : 1 }")
  public abstract CompanyProfile findByIdAndWareHouseDetailsWarehouseName(String paramString1, String paramString2);
  
  public abstract List<CompanyProfile> findByCreateUserOrderByCreateDateDesc(String paramString);
  
  public abstract CompanyProfile findByCode(String paramString);
  
  public abstract long countByCreateUser(String paramString);
}
