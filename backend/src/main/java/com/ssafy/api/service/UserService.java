package com.ssafy.api.service;

import java.util.List;

import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.db.entity.User;

public interface UserService {
	User signUp(UserRegisterPostReq userRegisterInfo);
	User getUserByUserno(int userno);
	User getUserByEmail(String email);
	User modifyUser(int userno, UserRegisterPostReq userModifyInfo);
	boolean deleteUser(int userno);
	List<User> getUserList();
}
