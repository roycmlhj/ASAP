package com.ssafy.db.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.ssafy.api.request.StudyBoardPutReq;
import com.ssafy.db.entity.StudyBoard;

@Repository
public interface StudyBoardRepositoryCustom {
	void modifyStudyBoard(StudyBoardPutReq studyBoardPutInfo);
	void deleteStudyBoard(int boardno);
	Optional<List<StudyBoard>> findStudyBoardByStudyno(int studyno);
}
