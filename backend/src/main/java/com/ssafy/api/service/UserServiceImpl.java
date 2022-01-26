package com.ssafy.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public User signUp(UserRegisterPostReq registerInfo) {
		User user = new User();
		user.setEmail(registerInfo.getEmail());
		user.setPassword(passwordEncoder.encode(registerInfo.getPassword()));
		user.setNickname(registerInfo.getNickname());
		user.setMainCategory(registerInfo.getMainCategory());
		user.setInterests(registerInfo.getInterests());
		return userRepository.save(user);
	}

	@Override
	public User getUserByEmail(String email) {
		User user = userRepository.findByEmail(email).get();
		return user;
	}

	@Override
	public User getUserByUserno(int userno) {
		User user = userRepository.findById(userno).get();
		return user;
	}

	@Override
	public User modifyUser(int userno, UserRegisterPostReq userModifyInfo) {
		User user = getUserByUserno(userno);
		System.out.println("getget"+user.toString());
		System.out.println(userModifyInfo.getPassword());
		if(!userModifyInfo.getPassword().isEmpty()) 
			user.setPassword(passwordEncoder.encode(userModifyInfo.getPassword()));
		System.out.println(userModifyInfo.getNickname());
		if(!userModifyInfo.getNickname().isEmpty()) 
			user.setNickname(userModifyInfo.getNickname());
		System.out.println(userModifyInfo.getMainCategory());
		if(!userModifyInfo.getMainCategory().isEmpty()) 
			user.setMainCategory(userModifyInfo.getMainCategory());
		if(!userModifyInfo.getInterests().isEmpty()) 
			user.setInterests(userModifyInfo.getInterests());
		System.out.println("4");
		if(!userModifyInfo.getImage().isEmpty()) 
			user.setImage(userModifyInfo.getImage());
		System.out.println("out");
		return userRepository.save(user);
	}

}
