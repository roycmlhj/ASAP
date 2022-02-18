package com.ssafy.api.response;

import com.ssafy.db.entity.UserHomework;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserHomeworkInfo {
	UserHomework userHomewrok;
	String nickname;
	public UserHomeworkInfo() {
		super();
	}
	public UserHomeworkInfo(UserHomework userHomewrok, String nickname) {
		this.userHomewrok = userHomewrok;
		this.nickname = nickname;
	}
}
