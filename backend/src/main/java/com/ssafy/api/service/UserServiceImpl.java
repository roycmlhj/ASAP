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
		System.out.println("회원가입 시작");
		User user = new User();
		user.setEmail(registerInfo.getEmail());
		user.setPassword(passwordEncoder.encode(registerInfo.getPassword()));
		user.setNickname(registerInfo.getNickname());
		user.setMainCategory(registerInfo.getMainCategory());
		user.setInterests(registerInfo.getInterests());
		System.out.println("회원가입 종료");
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

}
