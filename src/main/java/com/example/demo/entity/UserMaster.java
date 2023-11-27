package com.example.demo.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "USER_MASTER")
public class UserMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;

	private String fullName;

	private String email;

	private Long mobile;

	private String gender;

	private LocalDate dob;

	private long ssn;
	
	private String password;
	
	private String accStatus;
	
	private LocalDateTime createdDate;
	
	private LocalDateTime updatedDate;
	
	private LocalDateTime createdBy;
	
	private LocalDateTime updatedBy;

}
