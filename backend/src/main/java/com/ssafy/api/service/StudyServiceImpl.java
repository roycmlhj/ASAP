package com.ssafy.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.api.request.StudyCreatePostReq;
import com.ssafy.api.response.StudyInfo;
import com.ssafy.db.entity.Homework;
import com.ssafy.db.entity.Schedule;
import com.ssafy.db.entity.Study;
import com.ssafy.db.repository.HomeworkRepository;
import com.ssafy.db.repository.ScheduleRepository;
import com.ssafy.db.repository.StudyRepository;

@Service("studyService")
public class StudyServiceImpl implements StudyService {

	@Autowired
	StudyRepository studyRepository;
	@Autowired
	HomeworkRepository homeworkRepository;
	@Autowired
	ScheduleRepository scheduleRepository;
	
	@Override
	public boolean createStudy(StudyCreatePostReq studyInfo) {
		Study study = new Study();
		study.setStudyname(studyInfo.getStudyname());
		study.setCategory(studyInfo.getCategory());
		study.setDescription(studyInfo.getDescription());
		study.setMemberno(studyInfo.getMemberno());
		study.setMaker(studyInfo.getMaker());
		
		if(studyRepository.save(study) != null)
			return true;
		else
			return false;
	}

	@Override
	public List<Study> getStudyList(int userno) {
		List<Study> studyList = studyRepository.findByUserno(userno);
		
		return studyList;
	}

	@Override
	public StudyInfo getStudyInfo(int studyno, String studyName) {
		List<Homework> homeworkList = homeworkRepository.findByStudyno(studyno);
		Schedule schedule = scheduleRepository.findByStudyno(studyno);
		//List<String> memeberImage = ;
		
		return null;
	}

}
