package com.ssafy.api.service;

import com.ssafy.api.request.BoardCreatePostReq;
import com.ssafy.db.entity.Board;

public interface BoardService {
	Board createBoard(BoardCreatePostReq boardCreateInfo);
}
