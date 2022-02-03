package com.ssafy.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.api.request.BoardCreatePostReq;
import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.api.service.BoardService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "board API", tags = {"Board"})
@RestController
@RequestMapping("/api/v1/board")
public class BoardController {

	@Autowired
	BoardService boardService;
	
	@PostMapping("/create")
	@ApiOperation(value = "게시글 생성", notes = "게시글을 생성한다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<? extends BaseResponseBody> createBoard(
			@RequestBody @ApiParam(value="게시글 정보", required = true) BoardCreatePostReq boardInfo) {
		boardService.createBoard(boardInfo);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
}
