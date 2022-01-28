package com.ssafy.api.service;

import java.util.List;

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
		StringBuilder sb = new StringBuilder();
		for (String str : registerInfo.getInterests()) {
			sb.append("#"+str);
		}
		user.setInterests(sb.toString());
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
		if(!userModifyInfo.getPassword().equals("")) 
			user.setPassword(passwordEncoder.encode(userModifyInfo.getPassword()));
		if(!userModifyInfo.getNickname().equals("")) 
			user.setNickname(userModifyInfo.getNickname());
		if(!userModifyInfo.getMainCategory().equals("")) 
			user.setMainCategory(userModifyInfo.getMainCategory());
		if(!userModifyInfo.getInterests().isEmpty()) {
			StringBuilder sb = new StringBuilder();
			for (String str : userModifyInfo.getInterests()) {
				sb.append("#"+str);
			}
			user.setInterests(sb.toString());
		}
		if(userModifyInfo.getImage() != null) 
			user.setImage(userModifyInfo.getImage());
		return userRepository.save(user);
	}

	@Override
	public boolean deleteUser(int userno) {
		userRepository.deleteById(userno);
		// 삭제 후 boolean 출력 값 어떻게??
		return true;
	}

	@Override
	public List<User> getUserList() {
		List<User> userlist = userRepository.findAll();
		return userlist;
	}

}
