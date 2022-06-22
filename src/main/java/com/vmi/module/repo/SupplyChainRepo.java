package com.vmi.module.repo;

import com.vmi.module.model.Sch;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public abstract interface SupplyChainRepo
  extends MongoRepository<Sch, String>
{
  public abstract Sch findById(String paramString);
  
  public abstract List<Sch> findByParentWarehouseId(String paramString);
  
  public abstract List<Sch> findByParentWarehouseIdIn(List<String> paramList);
  
  public abstract Sch findByWareHouseAndParentWarehouseId(String paramString1, String paramString2);
}
