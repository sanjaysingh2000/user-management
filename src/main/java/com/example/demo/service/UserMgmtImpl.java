package com.example.demo.service;

import java.util.List;

import com.example.demo.binding.ActivateAccount;
import com.example.demo.binding.Login;
import com.example.demo.binding.User;

public class UserMgmtImpl implements UserMgmtService {

	@Override
	public boolean saveUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean activateUserAccount(ActivateAccount activateAcc) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserbyId(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteUserById(Integer userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changeAccountStatus(Integer userId, String accStatus) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String login(Login login) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String forgotPwd(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
