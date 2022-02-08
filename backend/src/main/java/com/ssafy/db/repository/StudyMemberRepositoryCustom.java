package com.ssafy.db.repository;

import org.springframework.stereotype.Repository;

import com.ssafy.api.request.AddTimeReq;
import com.ssafy.api.request.StudyAcceptPutReq;
import com.ssafy.db.entity.StudyMember;

@Repository
public interface StudyMemberRepositoryCustom {
	void acceptStudy(StudyAcceptPutReq acceptInfo);
	void rejectStudy(StudyAcceptPutReq acceptInfo);
	void kickStudyMember(int userno, int studyno);
	StudyMember findByUsernoNStudyNo(int userno, int studyno);
	StudyMember findByStudynoMandate(int studyno);
	void studyLeaderMandate(int userno, int studyno);
	void addTime(int userno, int studyno, String time);
}
