package com.vmi.module.repo;import com.vmi.module.model.Users;import org.springframework.data.mongodb.repository.MongoRepository;public abstract interface UsersRepo  extends MongoRepository<Users, String>{  public abstract Users findById(String paramString);    public abstract Users findByEmpCode(String paramString);}