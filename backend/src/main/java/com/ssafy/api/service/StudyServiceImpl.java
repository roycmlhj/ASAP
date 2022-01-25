package com.ssafy.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.api.request.StudyCreatePostReq;
import com.ssafy.db.entity.Study;
import com.ssafy.db.repository.StudyRepository;

@Service("studyService")
public class StudyServiceImpl implements StudyService {

	@Autowired
	StudyRepository studyRepository;
	
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

}
