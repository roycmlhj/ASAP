package com.ssafy.db.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.ssafy.api.request.StudyAcceptPutReq;
import com.ssafy.db.entity.StudyMember;

@Repository
public interface StudyMemberRepositoryCustom {
	void acceptStudy(StudyAcceptPutReq acceptInfo);
	void rejectStudy(StudyAcceptPutReq acceptInfo);
	void kickStudyMember(int userno, int studyno);
	StudyMember findByUsernoNStudyNo(int userno, int studyno);
	Optional<StudyMember> findByStudynoMandate(int studyno);
	void studyLeaderMandate(int userno, int studyno);
	void addTime(int userno, int studyno, String time);
	List<Integer> findStudynobyuserno(int userno);
}
