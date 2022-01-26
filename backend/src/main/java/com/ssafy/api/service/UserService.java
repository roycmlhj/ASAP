package com.ssafy.api.service;

import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.db.entity.User;

public interface UserService {
	User signUp(UserRegisterPostReq userRegisterInfo);
	User getUserByUserno(int userno);
	User getUserByEmail(String email);
	User modifyUser(int userno, UserRegisterPostReq userModifyInfo); 
}
