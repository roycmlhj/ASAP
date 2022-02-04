package com.ssafy.api.response;

import lombok.Setter;

import com.ssafy.db.entity.Homework;

import lombok.Getter;

@Getter
@Setter
public class HomeworkNNickname {
	Homework homework;
	String nickname;
	
	public HomeworkNNickname() {
		super();
	}

	public HomeworkNNickname(Homework homework, String nickname) {
		this.homework = homework;
		this.nickname = nickname;
	}
}
