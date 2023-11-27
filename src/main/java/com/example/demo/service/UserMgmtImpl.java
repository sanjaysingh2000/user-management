package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.demo.binding.ActivateAccount;
import com.example.demo.binding.Login;
import com.example.demo.binding.User;
import com.example.demo.entity.UserMaster;
import com.example.demo.repo.UserMasterRepo;

@Service
public class UserMgmtImpl implements UserMgmtService {

	@Autowired
	private UserMasterRepo userMasterRepo;

	@Override
	public boolean saveUser(User user) {

		UserMaster entity = new UserMaster();

		BeanUtils.copyProperties(user, entity);

		entity.setPassword(generateRandompwd());
		entity.setAccStatus("In-Active");

		UserMaster save = userMasterRepo.save(entity);

		// to send registration email

		return save.getUserId() != null;
	}

	@Override
	public boolean activateUserAccount(ActivateAccount activateAcc) {

		UserMaster entity = new UserMaster();
		entity.setEmail(activateAcc.getEmail());
		entity.setPassword(activateAcc.getTempPwd());

		// select * from user master where email = ? and password = ?
		Example<UserMaster> of = Example.of(entity);

		List<UserMaster> findAll = userMasterRepo.findAll(of);

		if (findAll.isEmpty()) {
			return false;
		} else {
			UserMaster userMaster = findAll.get(0);
			userMaster.setPassword(activateAcc.getNewPwd());
			userMaster.setAccStatus("Active");
			userMasterRepo.save(userMaster);
			return true;
		}

	}

	@Override
	public List<User> getAllUsers() {
		List<UserMaster> findAll = userMasterRepo.findAll();

		List<User> users = new ArrayList<>();
		for (UserMaster entity : findAll) {

			User user = new User();
			BeanUtils.copyProperties(entity, user);
			users.add(user);
		}
		return users;
	}

	@Override
	public User getUserbyId(Integer userId) {
		Optional<UserMaster> findById = userMasterRepo.findById(userId);
		if (findById.isPresent()) {
			User user = new User();
			UserMaster userMaster = findById.get();
			BeanUtils.copyProperties(userMaster, user);
			return user;
		}

		return null;
	}

	@Override
	public boolean deleteUserById(Integer userId) {
		try {
			userMasterRepo.deleteById(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean changeAccountStatus(Integer userId, String accStatus) {
		// TODO Auto-generated method stub

		Optional<UserMaster> findById = userMasterRepo.findById(userId);
		if (findById.isPresent()) {
			UserMaster userMaster = findById.get();
			userMaster.setAccStatus(accStatus);
			userMasterRepo.save(userMaster);
			return true;
		}
		return false;
	}

	@Override
	public String login(Login login) {

		UserMaster entity = new UserMaster();

		entity.setEmail(login.getEmail());
		entity.setPassword(login.getPassword());

		Example<UserMaster> of = Example.of(entity);
		List<UserMaster> findAll = userMasterRepo.findAll();

		if (findAll.isEmpty()) {
			return "Invalid Credentials";
		} else {
			UserMaster userMaster = findAll.get(0);
			if (userMaster.getAccStatus().equals("Active")) {
				return "Success";
			} else {
				return "Account not activated";
			}
		}

	}

	@Override
	public String forgotPwd(String email) {
		 UserMaster entity = userMasterRepo.findByEmail(email);
		 
		 if(entity == null) {
			 
			 return "Invalid Email";
			 
		 }
		 
		 //todo : send pswd to user in email
		 return null;
	}

	private String generateRandompwd() {

		String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
		String numbers = "0123456789";

		String alphaNumeric = upperAlphabet + lowerAlphabet + numbers;

		StringBuilder sb = new StringBuilder();

		Random random = new Random();

		int length = 6;

		for (int i = 0; i < length; i++) {

			int index = random.nextInt(alphaNumeric.length());

			char randomChar = alphaNumeric.charAt(index);
			sb.append(randomChar);
		}

		String randomString = sb.toString();
		System.out.println("Random String is: " + randomString);
		return randomString;
	}

}