package com.vmi.module.repo;

import com.vmi.module.model.UomMaster;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public abstract interface UomRepo
  extends MongoRepository<UomMaster, String>
{
  public abstract UomMaster findByIdOrderByCreateDateDesc(String paramString);
  
  public abstract List<UomMaster> findAllByOrderByCreateDateDesc();
  
  public abstract List<UomMaster> findByItemModel(String paramString);
}
