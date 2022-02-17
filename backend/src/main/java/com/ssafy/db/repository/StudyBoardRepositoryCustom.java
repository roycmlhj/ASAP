package com.ssafy.db.repository;

import org.springframework.stereotype.Repository;

import com.ssafy.api.request.StudyBoardPutReq;

@Repository
public interface StudyBoardRepositoryCustom {
	void modifyStudyBoard(StudyBoardPutReq studyBoardPutInfo);
	void deleteStudyBoard(int boardno);
}
