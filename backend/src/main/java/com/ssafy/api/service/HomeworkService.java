package com.ssafy.api.service;

import java.util.List;

import com.ssafy.api.request.HomeworkCreatePostReq;
import com.ssafy.api.request.HomeworkPutReq;
import com.ssafy.db.entity.Homework;

public interface HomeworkService {

	boolean createHomework(HomeworkCreatePostReq homeworkInfo);
	List<Homework> getHomeworkList(int studyno);
	boolean modifyHomework(HomeworkPutReq homeworkPutInfo);
	boolean deletehomework(int homeworkno);
	Homework getSHomeworkDetail(int homeworkno);
}
