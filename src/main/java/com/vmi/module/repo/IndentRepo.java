package com.vmi.module.repo;

import com.vmi.module.model.IndentDetails;
import com.vmi.module.model.WareHouseDetails;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public abstract interface IndentRepo
  extends MongoRepository<IndentDetails, String>
{
  public abstract IndentDetails findById(String paramString);
  
  public abstract List<IndentDetails> findAllByOrderByCreateDateDesc();
  
  public abstract List<IndentDetails> findByWareHouse(String paramString);
  
  public abstract List<IndentDetails> findByWareHouseIn(List<WareHouseDetails> paramList);
  
  public abstract List<IndentDetails> findByWareHouseAndIndentPriority(String paramString1, String paramString2);
  
  public abstract List<IndentDetails> findByWareHouseAndIndentDateBetween(String paramString, Date paramDate1, Date paramDate2);
  
  public abstract List<IndentDetails> findByIndentDate(Date paramDate);
  
  public abstract long countByCreateUser(String paramString);
  
  public abstract List<IndentDetails> findByCreateUserOrderByCreateDateDesc(String paramString, Pageable paramPageable);
  
  public abstract List<IndentDetails> findByIndentStatusAndCreateUserOrderByCreateDateDesc(String paramString1, String paramString2, Pageable paramPageable);
  
  public abstract List<IndentDetails> findByIndentStatusOrderByCreateDateDesc(String paramString, Pageable paramPageable);
}
