package com.vmi.module.repo;import com.vmi.module.model.ItemCategory;import java.util.List;import org.springframework.data.mongodb.repository.MongoRepository;public abstract interface ItemCategoryRepo  extends MongoRepository<ItemCategory, String>{  public abstract ItemCategory findById(String paramString);    public abstract List<ItemCategory> findByItemgroup(String paramString);}