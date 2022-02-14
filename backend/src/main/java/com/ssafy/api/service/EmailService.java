package com.ssafy.api.service;

import com.ssafy.db.entity.EmailConfirm;

public interface EmailService {
	String sendSimpleMessage(String to) throws Exception;
	EmailConfirm getByEmail(String email);
}
