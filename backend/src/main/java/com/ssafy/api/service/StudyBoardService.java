package com.ssafy.api.service;

import java.util.List;

import com.ssafy.api.request.StudyBoardCreatePostReq;
import com.ssafy.db.entity.StudyBoard;

public interface StudyBoardService {
	boolean createStudyBoard(StudyBoardCreatePostReq studyBoardInfo);
	List<StudyBoard> getStudyBoardList(int studyno);
}
