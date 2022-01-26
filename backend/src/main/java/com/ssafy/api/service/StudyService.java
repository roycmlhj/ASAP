package com.ssafy.api.service;

import java.util.List;

import com.ssafy.db.entity.Study;

public interface StudyService {
	List<Study> getStudyList(int userno);
}
