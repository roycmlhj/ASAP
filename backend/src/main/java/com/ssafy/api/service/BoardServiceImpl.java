package com.ssafy.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.api.request.BoardCreatePostReq;
import com.ssafy.db.entity.Board;
import com.ssafy.db.entity.Study;
import com.ssafy.db.repository.BoardRepository;
import com.ssafy.db.repository.StudyRepository;

@Service("boardService")
public class BoardServiceImpl implements BoardService {
	@Autowired
	BoardRepository boardReposiroy;
	@Autowired
	StudyRepository studyRepository;

	@Override
	public Board createBoard(BoardCreatePostReq boardCreateInfo) {
		Board board = new Board();
		Study study =  studyRepository.findById(boardCreateInfo.getStudyno()).get();
		board.setUserno(boardCreateInfo.getUserno());
		board.setStudyno(boardCreateInfo.getStudyno());
		board.setBoardname(boardCreateInfo.getBoardname());
		board.setBoarddescription(boardCreateInfo.getBoarddescription());
		board.setLink(boardCreateInfo.getLink());
		board.setContactlink(boardCreateInfo.getContactlink());
		board.setInterests(study.getInterests());
		board.setCategory(study.getCategory());
		board.setMaxmember(study.getMemberno());
		return boardReposiroy.save(board);
	}

}
