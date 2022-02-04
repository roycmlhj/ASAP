package com.ssafy.db.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.ssafy.api.request.HomeworkPutReq;
import com.ssafy.db.entity.Homework;

@Repository
public interface HomeworkRepositoryCustom {
	Optional<List<Homework>> findByStudyno(int studyno);
	// 민추가
	Optional<List<Homework>> findHomeworkByuserno(int userno, int flag);
	//추가
	void modifyHomework(HomeworkPutReq homeworkPutInfo);
	void deleteHomework(int homeworkno);
}
