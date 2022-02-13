package com.ssafy.api.response;

import lombok.Setter;

import com.ssafy.db.entity.Homework;

import lombok.Getter;

@Getter
@Setter
public class HomeworkNStudy {
	Homework homework;
	String studyname;
	
	public HomeworkNStudy() {
		super();
	}

	public HomeworkNStudy(Homework homework, String studyname) {
		this.homework = homework;
		this.studyname = studyname;
	}
}
