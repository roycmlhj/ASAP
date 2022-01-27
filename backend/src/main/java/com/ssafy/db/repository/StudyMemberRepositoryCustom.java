package com.ssafy.db.repository;

import org.springframework.stereotype.Repository;

import com.ssafy.api.request.StudyAcceptPutReq;

@Repository
public interface StudyMemberRepositoryCustom {
	void acceptStudy(StudyAcceptPutReq acceptInfo);
	void rejectStudy(StudyAcceptPutReq acceptInfo);
}
