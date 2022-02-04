package com.ssafy.api.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ssafy.api.request.BoardCreatePostReq;
import com.ssafy.api.response.BoardMember;
import com.ssafy.api.response.BoardPaging;
import com.ssafy.db.entity.Board;
import com.ssafy.db.entity.Study;
import com.ssafy.db.entity.StudyMember;
import com.ssafy.db.repository.BoardRepository;
import com.ssafy.db.repository.StudyMemberRepository;
import com.ssafy.db.repository.StudyRepository;

@Service("boardService")
public class BoardServiceImpl implements BoardService {
	@Autowired
	BoardRepository boardRepository;
	@Autowired
	StudyRepository studyRepository;
	@Autowired
	UserService userService;

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
		board.setNickname(userService.getNicknameByUserno(boardCreateInfo.getUserno()));
		return boardRepository.save(board);
	}
	
	@Override
	public Board getBoardByBoardno(int boardno) {
		Board board = boardRepository.findById(boardno).get();
		return board;
	}
	
	@Override
	public Board modifyBoard(int boardno, BoardCreatePostReq boardModifyInfo) {
		Board board = getBoardByBoardno(boardno);
		Study study = studyRepository.findById(boardModifyInfo.getStudyno()).get();
		board.setStudyno(boardModifyInfo.getStudyno());
		board.setBoardname(boardModifyInfo.getBoardname());
		board.setBoarddescription(boardModifyInfo.getBoarddescription());
		board.setLink(boardModifyInfo.getLink());
		board.setContactlink(boardModifyInfo.getContactlink());
		board.setCategory(study.getCategory());
		board.setMaxmember(study.getMemberno());
		return boardRepository.save(board);
	}

	@Override
	public boolean deleteBoard(int boardno) {
		boardRepository.deleteById(boardno);
		return true;
	}

	@Override
	public Page<BoardPaging> boardList(Pageable pageRequest) {
		Page<Board> boardList = boardRepository.findAll(pageRequest);
		Page<BoardPaging> pagingList = boardList.map(
				board -> new BoardPaging(
						board.getBoardno(),
						board.getBoardname(),
						board.getUserno(),
						board.getNickname(),
						board.getCategory(),
						board.getIsRecruit(),
						board.getMaxmember(),
						(int)studyRepository.countRecruteUser(board.getStudyno()),
						board.getTimestamp(),
						board.getInterests()
				));
		return pagingList;
	}

	@Override
	@Transactional
	public List<BoardPaging> boardSearch(String type, String keyword, Pageable pageRequest) {
		List<Board> boardList = new ArrayList<Board>();
		switch (type) {
		case "category":
			boardList = boardRepository.findByCategoryContaining(keyword, pageRequest);
			break;
		case "nickname":
			boardList = boardRepository.findByNicknameContaining(keyword, pageRequest);
			break;
		case "boardname":
			boardList = boardRepository.findByBoardnameContaining(keyword, pageRequest);
			break;
		default:
			break;
		}
		List<BoardPaging> pagingList = new ArrayList<BoardPaging>();
		for (Board board : boardList) {
			BoardPaging bp = new BoardPaging(
					board.getBoardno(), 
					board.getBoardname(), 
					board.getUserno(), 
					board.getNickname(), 
					board.getCategory(), 
					board.getIsRecruit(), 
					board.getMaxmember(), 
					(int)studyRepository.countRecruteUser(board.getStudyno()), 
					board.getTimestamp(),
					board.getInterests());
			pagingList.add(bp);
		}
		return pagingList;
	}
}
