package com.ssafy.api.service;

import java.util.List;

import com.ssafy.api.request.StudyBoardCreatePostReq;
import com.ssafy.api.request.StudyBoardPutReq;
import com.ssafy.db.entity.StudyBoard;

public interface StudyBoardService {
	boolean createStudyBoard(StudyBoardCreatePostReq studyBoardInfo);
	List<StudyBoard> getStudyBoardList(int studyno);
	StudyBoard getStudyBoardDetail(int boardno);
	boolean modifyStudyBoard(StudyBoardPutReq studyBoardPutInfo);
	boolean deleteStudyBoard(int boardno);
}
