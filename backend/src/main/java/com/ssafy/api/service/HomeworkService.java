package com.ssafy.api.service;

import java.util.List;

import com.ssafy.api.request.HomeworkCreatePostReq;
import com.ssafy.db.entity.Homework;

public interface HomeworkService {

	boolean createHomework(HomeworkCreatePostReq homeworkInfo);
	List<Homework> getHomeworkList(int studyno);
}
