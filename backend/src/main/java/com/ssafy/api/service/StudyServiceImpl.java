package com.ssafy.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.db.entity.Study;
import com.ssafy.db.repository.StudyRepository;

@Service("studyService")
public class StudyServiceImpl implements StudyService {
	@Autowired
	StudyRepository studyRepository;

	@Override
	public List<Study> getStudyList(int userno) {
		List<Study> studyList = studyRepository.findByUserno(userno);
		return studyList;
	}

}
