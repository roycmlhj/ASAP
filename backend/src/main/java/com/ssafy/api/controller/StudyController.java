package com.ssafy.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.api.request.StudyCreatePostReq;
import com.ssafy.api.response.StudyListRes;
import com.ssafy.api.service.StudyService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Study;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

@Api(value = "study API", tags = {"Study"})
@RestController
@RequestMapping("/api/v1/study")
public class StudyController {

	@Autowired
	StudyService studyService;
	
	/*
	 *  /study/create post
	 *  /study/{userno} get
	 *  /study/list/{userno} get
	 *  /study/simple-detail/{studyno}
	 *  /study/all-user/{studyno}
	 *  /study/apply post
	 *  /study/accept put
	 */
	
	@PostMapping("/create")
	@ApiOperation(value = "스터디 방 생성", notes = "스터디 방에 대한 정보를 받고 스터디 방을 생성한다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 401, message = "실패"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<? extends BaseResponseBody> create(
			@RequestBody @ApiParam(value="생성 스터디 방 정보", required = true) StudyCreatePostReq studyInfo){
		System.out.println("study create start");
		
		if(studyService.createStudy(studyInfo))
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "성공"));
		else
			return ResponseEntity.status(200).body(BaseResponseBody.of(401, "실패"));
	}
	
	
	@GetMapping("/list/{userno}")
	@ApiOperation(value = "참여 중인 스터디 목록", notes = "회원 번호에 따른 참여 스터디 목록을 반환한다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 401, message = "실패"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<StudyListRes> getStudyList(@PathVariable("userno") @ApiParam(value = "유저 pk", required = true) int userno){
		
		List<Study> studyList = studyService.getStudyList(userno);
		
		return ResponseEntity.status(200).body(StudyListRes.of(studyList));
	};
}
