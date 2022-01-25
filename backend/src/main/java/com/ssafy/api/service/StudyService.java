package com.ssafy.api.service;

import java.util.List;

import com.ssafy.api.request.StudyAcceptPutReq;
import com.ssafy.api.request.StudyApplyPostReq;
import com.ssafy.api.request.StudyCreatePostReq;
import com.ssafy.api.response.StudyInfo;
import com.ssafy.db.entity.Study;

public interface StudyService {
	boolean createStudy(StudyCreatePostReq studyInfo);
	List<Study> getStudyList(int userno);
	StudyInfo getStudyInfo(int studyno, String studyName);
	Study getStudyInfo(int studyno);
	boolean applyStudy(StudyApplyPostReq applyInfo);
	boolean acceptStudy(StudyAcceptPutReq acceptInfo);
}
