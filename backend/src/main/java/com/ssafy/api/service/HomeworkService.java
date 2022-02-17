package com.ssafy.api.service;

import java.util.List;

import com.ssafy.api.request.HomeworkCreatePostReq;
import com.ssafy.api.request.HomeworkPutReq;
import com.ssafy.api.response.HomeworkNStudy;
import com.ssafy.db.entity.Homework;
import com.ssafy.db.entity.UserHomework;

public interface HomeworkService {

	boolean createHomework(HomeworkCreatePostReq homeworkInfo);
	List<Homework> getHomeworkList(int studyno);
	boolean modifyHomework(HomeworkPutReq homeworkPutInfo);
	boolean deletehomework(int homeworkno);
	List<HomeworkNStudy> getUserHomeworkList(int userno, int flag);
	Homework getSHomeworkDetail(int homeworkno);
	List<UserHomework> getUploadHomework(int homeworkno);
	int getDoHomeworkCnt(int userno);
}
