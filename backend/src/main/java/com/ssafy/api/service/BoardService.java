package com.ssafy.api.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ssafy.api.request.BoardCreatePostReq;
import com.ssafy.api.response.BoardPaging;
import com.ssafy.db.entity.Board;

public interface BoardService {
	Board createBoard(BoardCreatePostReq boardCreateInfo);
	Board getBoardByBoardno(int boardno);
	Board modifyBoard(int boardno, BoardCreatePostReq boardCreateInfo);
	boolean deleteBoard(int boardno);
	Page<BoardPaging> boardList(Pageable pageRequest);
	List<BoardPaging> boardSearch(String type, String keyword, Pageable pageRequest);
	List<Integer> getBoardnoByUserno(int userno);
	int updateHit(int boardno);
}
