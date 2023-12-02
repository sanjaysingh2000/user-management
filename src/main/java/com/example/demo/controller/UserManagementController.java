package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.binding.ActivateAccount;
import com.example.demo.binding.Login;
import com.example.demo.binding.User;
import com.example.demo.service.UserMgmtService;

import jakarta.websocket.server.PathParam;

@RestController
public class UserManagementController {
	
	@Autowired
	private UserMgmtService service;
	
	@GetMapping("/user")
	public ResponseEntity<String> userReg(@RequestBody User user){
		
		boolean saveUser = service.saveUser(user);
		if(saveUser) {
			return new ResponseEntity<>("Registration Success", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Registration Failed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/activate")
	public ResponseEntity<String> activateAccount(@RequestBody ActivateAccount acc){
		
		boolean isActivated= service.activateUserAccount(acc);
		if(isActivated) {
			return new ResponseEntity<>("Account Activated", HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Invalid Temporary Pwd", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers(){
		List<User> allUsers = service.getAllUsers();
		return new ResponseEntity<>(allUsers, HttpStatus.OK);
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable Integer userId){
		User user = service.getUserbyId(userId);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@DeleteMapping("/user/{userId}")
	public ResponseEntity<String> deleteUserById(@PathVariable Integer userId){
		
		boolean deleteUserById = service.deleteUserById(userId);
		if(deleteUserById) {
			return new ResponseEntity<>("Delete Successfull", HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Delete Failed", HttpStatus.INTERNAL_SERVER_ERROR); 
		}
	}
	
	
	@GetMapping("/user/{user}/{status}")
	public ResponseEntity<String> statusChanged(@PathVariable Integer userId, @PathVariable String status){
		
		boolean changeAccountStatus = service.changeAccountStatus(userId, status);
		if(changeAccountStatus) {
			return new ResponseEntity<>("Status Changed", HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Status Not Changed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@PathVariable Login login){
		
		String status = service.login(login);
		return new ResponseEntity<>("Login Successfull", HttpStatus.OK); 
	}
	
	@GetMapping("/forgot/{email}")
	public ResponseEntity<String> forgotPassword(@PathVariable String email){
		
	String status = service.forgotPwd(email);	
	return new ResponseEntity<>(status, HttpStatus.OK);
	}
	
	
	

}
