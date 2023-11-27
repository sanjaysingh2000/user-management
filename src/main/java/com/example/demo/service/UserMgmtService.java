package com.example.demo.service;

import java.util.List;
import java.util.Map;

import com.example.demo.binding.ActivateAccount;
import com.example.demo.binding.Login;
import com.example.demo.binding.User;

public interface UserMgmtService {

	public boolean saveUser(User user);
	
	public boolean activateUserAccount(ActivateAccount activateAcc);
	
	public List<User> getAllUsers();
	
	public User getUserbyId(Integer userId);
	
	public boolean deleteUserById(Integer userId);
	
	public boolean changeAccountStatus(Integer userId, String accStatus);
	
	public String login(Login login);
	
	public String forgotPwd(String email);
	
	
	
}
