package com.vmi.module.repo;

import com.vmi.module.model.WareHouseDetails;
import com.vmi.module.model.WareHouseMapping;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public abstract interface WareHouseMappingRepo
  extends MongoRepository<WareHouseMapping, String>
{
  public abstract WareHouseMapping findById(String paramString);
  
  public abstract List<WareHouseMapping> findByWareHouse(String paramString);
  
  public abstract WareHouseMapping findByItemMaster(String paramString);
  
  public abstract List<WareHouseMapping> findByWareHouseIn(List<WareHouseDetails> paramList);
  
  public abstract WareHouseMapping findByWareHouseAndItemMaster(String paramString1, String paramString2);
}
