package com.example.user.adduser.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.user.adduser.entity.AddUserEntity;

@Repository
public interface EditUserRepo extends JpaRepository<AddUserEntity, Long>{

	AddUserEntity findByEmail(String email);

	Optional<AddUserEntity> getByEmail(String email);



}
