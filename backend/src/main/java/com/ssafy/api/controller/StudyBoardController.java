package com.ssafy.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.api.request.StudyBoardCreatePostReq;
import com.ssafy.api.request.StudyBoardPutReq;
import com.ssafy.api.response.StudyBoardListRes;
import com.ssafy.api.response.StudyBoardRes;
import com.ssafy.api.service.StudyBoardService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.StudyBoard;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "StudyBoard API", tags = {"StudyBoard"})
@RestController
@RequestMapping("/api/v1/study_board")
public class StudyBoardController {
	@Autowired
	StudyBoardService studyBoardService;
	
	@PostMapping("/create")
	@ApiOperation(value = "스터디 게시글 생성", notes = "스터디 게시글을 생성한다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 401, message = "실패"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<? extends BaseResponseBody> create(
			@RequestBody @ApiParam(value="생성 스터디 게시글 정보", required = true) StudyBoardCreatePostReq studyBoardInfo){	
		if(studyBoardService.createStudyBoard(studyBoardInfo))
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "성공"));
		else
			return ResponseEntity.status(401).body(BaseResponseBody.of(401, "실패"));	
	}
	
	
	@GetMapping("/boardlist/{studyno}")
	@ApiOperation(value = "스터디의 게시글 리스트", notes = "스터디 내의 게시글 리스트를 반환해준다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 401, message = "실패"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<StudyBoardListRes> getStudyBoardList(@PathVariable("studyno") @ApiParam(value = "study pk", required = true) int studyno){
		List<StudyBoard> studyBoardList = studyBoardService.getStudyBoardList(studyno);
		
		return ResponseEntity.status(200).body(StudyBoardListRes.of(studyBoardList));
	};
	
	

	@GetMapping("/boardlist/detail/{boardno}")
	@ApiOperation(value = "스터디의 게시글 상세정보", notes = "스터디 내의 게시글 상세정보를 반환해준다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 401, message = "실패"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<StudyBoardRes> getStudyBoardDetail(@PathVariable("boardno") @ApiParam(value = "board pk", required = true) int boardno){
		StudyBoard studyBoard = studyBoardService.getStudyBoardDetail(boardno);
		
		return ResponseEntity.status(200).body(StudyBoardRes.of(studyBoard));
	};
	
	@PutMapping("/modify")
	@ApiOperation(value = "스터디 게시글 수정", notes = "스터디 게시글을 수정한다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 401, message = "실패"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<? extends BaseResponseBody> accept(
			@RequestBody @ApiParam(value="스터디 게시물 수정 정보", required = true) StudyBoardPutReq studyBoardPutInfo){
		if(studyBoardService.modifyStudyBoard(studyBoardPutInfo))
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "성공"));
		else
			return ResponseEntity.status(401).body(BaseResponseBody.of(401, "실패"));
	}
	
	@DeleteMapping("/delete/{boardno}")
	@ApiOperation(value = "스터디 게시글 삭제", notes = "스터디 게시글을 삭제한다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "실패"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<? extends BaseResponseBody> deleteUser(
			@PathVariable("boardno") @ApiParam(value = "삭제할 board pk", required = true) int boardno){
		if(studyBoardService.deleteStudyBoard(boardno))
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
		else
			return ResponseEntity.status(401).body(BaseResponseBody.of(401, "실패"));
	}
}
