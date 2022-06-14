package com.example.user.adduser.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.user.adduser.entity.AddUserEntity;
@Repository
public interface AddUserRepo extends JpaRepository<AddUserEntity, Long> {


	
}
