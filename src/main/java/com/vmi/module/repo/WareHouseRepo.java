package com.vmi.module.repo;

import com.vmi.module.model.WareHouseDetails;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public abstract interface WareHouseRepo
  extends MongoRepository<WareHouseDetails, String>
{
  public abstract WareHouseDetails findById(String paramString);
  
  public abstract List<WareHouseDetails> findByCompanyProfile(String paramString);
  
  public abstract WareHouseDetails findByWarehouseCode(String paramString);
  
  public abstract long countByCreateBy(String paramString);
}
