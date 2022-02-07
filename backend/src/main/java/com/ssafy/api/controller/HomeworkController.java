package com.ssafy.api.controller;

import java.util.ArrayList;
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

import com.ssafy.api.request.HomeworkCreatePostReq;
import com.ssafy.api.request.HomeworkPutReq;
import com.ssafy.api.request.StudyBoardCreatePostReq;
import com.ssafy.api.request.StudyBoardPutReq;
import com.ssafy.api.response.HomeworkListRes;
import com.ssafy.api.response.HomeworkNNickname;
import com.ssafy.api.response.HomeworkRes;
import com.ssafy.api.response.StudyBoardListRes;
import com.ssafy.api.response.StudyBoardRes;
import com.ssafy.api.service.HomeworkService;
import com.ssafy.api.service.UserService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Homework;
import com.ssafy.db.entity.StudyBoard;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Homework API", tags = {"Homework"})
@RestController
@RequestMapping("/api/v1/homework")
public class HomeworkController {
	@Autowired
	HomeworkService homeworkService;
	@Autowired
	UserService userService;
	
	@PostMapping("/create")
	@ApiOperation(value = "과제 생성", notes = "과제 글을 생성한다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 401, message = "실패"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<? extends BaseResponseBody> create(
			@RequestBody @ApiParam(value="생성할 과제 정보", required = true) HomeworkCreatePostReq HomeworkInfo){	
		if(homeworkService.createHomework(HomeworkInfo))
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "성공"));
		else
			return ResponseEntity.status(401).body(BaseResponseBody.of(401, "실패"));	
	}
	
	///homeworklist/{studyno}
	@GetMapping("/homeworklist/{studyno}")
	@ApiOperation(value = "과제 글 리스트", notes = "스터디 내의 과제 글 리스트를 반환해준다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 401, message = "실패"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<HomeworkListRes> getHomeworkList(@PathVariable("studyno") @ApiParam(value = "study pk", required = true) int studyno){
		List<Homework> homeworkList = homeworkService.getHomeworkList(studyno);
		List<HomeworkNNickname> homeworkNNicknameList = new ArrayList<>();
		
		for(int i = 0; i < homeworkList.size(); i++) {
			homeworkNNicknameList.add(new HomeworkNNickname(homeworkList.get(i), userService.getUserNickname(homeworkList.get(i).getUserno())));
		}
		
		return ResponseEntity.status(200).body(HomeworkListRes.of(homeworkNNicknameList));
	};
	
	@GetMapping("/homework/detail/{homeworkno}")
	@ApiOperation(value = "과제 글 상세정보", notes = "과제 글 상세정보를 반환해준다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 401, message = "실패"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<HomeworkRes> getStudyBoardDetail(@PathVariable("homeworkno") @ApiParam(value = "homework pk", required = true) int homeworkno){
		Homework homework = homeworkService.getSHomeworkDetail(homeworkno);
		String nickname = userService.getUserNickname(homework.getUserno());
		return ResponseEntity.status(200).body(HomeworkRes.of(homework, nickname));
	};
	
	@PutMapping("/modify")
	@ApiOperation(value = "과제 글 수정", notes = "과제 글을 수정한다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 401, message = "실패"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<? extends BaseResponseBody> accept(
			@RequestBody @ApiParam(value="과제 글 수정 정보", required = true) HomeworkPutReq homeworkPutInfo){
		if(homeworkService.modifyHomework(homeworkPutInfo))
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "성공"));
		else
			return ResponseEntity.status(401).body(BaseResponseBody.of(401, "실패"));
	}
	
	@DeleteMapping("/delete/{homeworkno}")
	@ApiOperation(value = "과제 글 삭제", notes = "과제 글을 삭제한다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "실패"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<? extends BaseResponseBody> deleteHomework(
			@PathVariable("homeworkno") @ApiParam(value = "삭제할 homework pk", required = true) int homeworkno){
		if(homeworkService.deletehomework(homeworkno))
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
		else
			return ResponseEntity.status(401).body(BaseResponseBody.of(401, "실패"));
	}
	
	
}
