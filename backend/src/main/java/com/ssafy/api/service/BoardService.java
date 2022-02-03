package com.ssafy.api.service;

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
}
