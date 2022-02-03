package com.ssafy.api.response;

import java.util.List;

import com.ssafy.db.entity.Homework;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudyInfo {
	String studyName;
	String category;
	List<Homework> homeworkList;
	String nextDate;
	List<String> memberImage;
}