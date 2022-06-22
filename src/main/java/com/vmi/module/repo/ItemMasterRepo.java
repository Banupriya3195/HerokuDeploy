package com.vmi.module.repo;

import com.vmi.module.model.ItemMaster;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public abstract interface ItemMasterRepo
  extends MongoRepository<ItemMaster, String>
{
  public abstract ItemMaster findById(String paramString);
  
  public abstract List<ItemMaster> findAllByOrderByCreateDateDesc();
  
  public abstract ItemMaster findByItemCode(String paramString);
  
  public abstract ItemMaster findByItemNameAndItemCodeAndItemDescription(String paramString1, String paramString2, String paramString3);
  
  public abstract long countByCreateBy(String paramString);
}
