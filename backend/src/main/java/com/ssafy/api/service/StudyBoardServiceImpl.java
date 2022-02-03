package com.ssafy.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.api.request.StudyBoardCreatePostReq;
import com.ssafy.db.entity.StudyBoard;
import com.ssafy.db.repository.StudyBoardRepository;

@Service("studyBoardService")
public class StudyBoardServiceImpl implements StudyBoardService {
	@Autowired
	StudyBoardRepository studyBoardRepository;

	@Override
	public boolean createStudyBoard(StudyBoardCreatePostReq studyBoardInfo) {
		StudyBoard studyBoard = new StudyBoard();
		studyBoard.setContent(studyBoardInfo.getContent());
		studyBoard.setStudyno(studyBoardInfo.getStudyno());
		studyBoard.setTitle(studyBoardInfo.getTitle());
		studyBoard.setType(studyBoardInfo.getType());
		studyBoard.setUserno(studyBoardInfo.getUserno());
		
		if(studyBoardRepository.save(studyBoard) != null)
			return true;
		
		return false;
	}

	@Override
	public List<StudyBoard> getStudyBoardList(int studyno) {
		List<StudyBoard> studyBoardList = studyBoardRepository.findByStudyno(studyno).get();
		
		return studyBoardList;
	}
}
