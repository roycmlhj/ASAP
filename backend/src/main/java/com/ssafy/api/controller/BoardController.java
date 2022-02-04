package com.ssafy.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.api.request.BoardCreatePostReq;
import com.ssafy.api.response.BoardMember;
import com.ssafy.api.response.BoardPaging;
import com.ssafy.api.response.BoardRes;
import com.ssafy.api.service.BoardService;
import com.ssafy.api.service.StudyService;
import com.ssafy.api.service.UserService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Board;

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
	@Autowired
	StudyService studyService;
	@Autowired
	UserService userService;
	
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
	
	@GetMapping("/{boardno}")
	@ApiOperation(value = "게시글 상세보기", notes = "게시글을 조회한다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<BoardRes> detailBoard(
			@PathVariable("boardno") @ApiParam(value = "조회할 게시판의 no", required = true) int boardno){
		Board board = boardService.getBoardByBoardno(boardno);
		List<BoardMember> list = userService.getBoardMember(board.getStudyno());
		return ResponseEntity.status(200).body(BoardRes.of(board, list));
	}
	
	@PostMapping("/{boardno}")
	@ApiOperation(value = "게시글 수정", notes = "게시글을 수정한다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<? extends BaseResponseBody> modifyBoard(
			@RequestBody @ApiParam(value="게시글 수정 정보", required = true) BoardCreatePostReq boardInfo, 
			@PathVariable("boardno") @ApiParam(value = "수정할 게시판의 no", required = true) int boardno) {
		boardService.createBoard(boardInfo);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
	
	@DeleteMapping("/{boardno}")
	@ApiOperation(value = "게시글 삭제", notes = "게시글을 삭제한다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<? extends BaseResponseBody> deleteBoard(
			@PathVariable("boardno") @ApiParam(value = "삭제할 게시판의 no", required = true) int boardno) {
		boardService.deleteBoard(boardno);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
	
	@GetMapping("/list")
	@ApiOperation(value = "게시글 목록", notes = "게시글을 목록을 불러온다") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<Page<BoardPaging>> boardList(
			@PageableDefault(size=5, sort="timestamp") @ApiParam(value = "페이징 객체", required = true) Pageable pageRequest) {
		Page<BoardPaging> pagingList = boardService.boardList(pageRequest);
		return ResponseEntity.status(200).body(pagingList);
	}
	
	@GetMapping("/list/search")
	@ApiOperation(value = "게시글 검색", notes = "게시글을 검색하고 목록을 불러온다") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<List<BoardPaging>> boardSearchList(
			@ApiParam(value = "검색 분류", required = true) String type, 
			@ApiParam(value = "검색 키워드", required = true) String keyword,
			@PageableDefault(size=5, sort="timestamp") @ApiParam(value = "검색할 게시물 정보", required = true) Pageable pageRequest) {
		List<BoardPaging> pagingList = boardService.boardSearch(type, keyword, pageRequest);
		return ResponseEntity.status(200).body(pagingList);
	}
}
