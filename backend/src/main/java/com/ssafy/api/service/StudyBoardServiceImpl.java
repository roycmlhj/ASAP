package com.ssafy.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.api.request.StudyBoardCreatePostReq;
import com.ssafy.api.request.StudyBoardPutReq;
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

	@Override
	public StudyBoard getStudyBoardDetail(int boardno) {
		StudyBoard studyBoard = studyBoardRepository.findById(boardno).get();
		
		return studyBoard;
	}

	@Override
	public boolean modifyStudyBoard(StudyBoardPutReq studyBoardPutInfo) {
		if(Optional.ofNullable(studyBoardRepository.findById(studyBoardPutInfo.getBoardno())).get() != null) {
			studyBoardRepository.modifyStudyBoard(studyBoardPutInfo);
			return true;
		}
		else
			return false;
	}

	@Override
	public boolean deleteStudyBoard(int boardno) {
		if(Optional.ofNullable(studyBoardRepository.findById(boardno)).get() != null) {
			studyBoardRepository.deleteStudyBoard(boardno);
			return true;
		}
		else {
			return false;
		}
	}
}
