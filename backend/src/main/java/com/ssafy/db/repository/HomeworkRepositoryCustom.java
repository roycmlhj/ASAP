package com.ssafy.db.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.ssafy.api.request.HomeworkPutReq;
import com.ssafy.db.entity.Homework;
import com.ssafy.db.entity.UserHomework;

@Repository
public interface HomeworkRepositoryCustom {
	void modifyHomework(HomeworkPutReq homeworkPutInfo);
	void deleteHomework(int homeworkno);
}
