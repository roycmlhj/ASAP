package com.ssafy.api.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ssafy.api.request.StudyBoardCreatePostReq;
import com.ssafy.api.request.StudyBoardPutReq;
import com.ssafy.db.entity.StudyBoard;

public interface StudyBoardService {
	boolean createStudyBoard(StudyBoardCreatePostReq studyBoardInfo, MultipartFile files);
	List<StudyBoard> getStudyBoardList(int studyno);
	StudyBoard getStudyBoardDetail(int boardno);
	boolean modifyStudyBoard(StudyBoardPutReq studyBoardPutInfo, MultipartFile files);
	boolean deleteStudyBoard(int boardno);
}
