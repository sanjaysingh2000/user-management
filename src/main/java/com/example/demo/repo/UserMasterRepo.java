package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.UserMaster;

public interface UserMasterRepo extends JpaRepository<UserMaster, Integer> {
	
	public UserMaster findByEmail(String email);

}
