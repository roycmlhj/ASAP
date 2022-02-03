package com.ssafy.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.api.request.HomeworkCreatePostReq;
import com.ssafy.db.entity.Homework;
import com.ssafy.db.repository.HomeworkRepository;

@Service("homeworkService")
public class HomeworkServiceImpl implements HomeworkService {
	@Autowired
	HomeworkRepository homeworkRepository;

	@Override
	public boolean createHomework(HomeworkCreatePostReq homeworkInfo) {
		Homework homework = new Homework();
		homework.setStudyno(homeworkInfo.getStudyno());
		homework.setUserno(homeworkInfo.getUserno());
		homework.setTitle(homeworkInfo.getTitle());
		homework.setEndDate(homeworkInfo.getEndDate());
		homework.setContent(homeworkInfo.getContent());
		
		if(homeworkRepository.save(homework) != null)
			return true;
		
		return false;
	}

	@Override
	public List<Homework> getHomeworkList(int studyno) {
		List<Homework> homeworkList = homeworkRepository.findByStudyno(studyno).get();
		
		return homeworkList;
	}
}
