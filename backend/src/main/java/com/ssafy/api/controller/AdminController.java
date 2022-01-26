package com.ssafy.api.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.api.response.UserInfoStudyListRes;
import com.ssafy.api.service.StudyService;
import com.ssafy.api.service.UserService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Study;
import com.ssafy.db.entity.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 관리자 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "관리자 API", tags = {"Admin"})
@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
	@Autowired
	UserService userService;
	@Autowired
	StudyService studyService;
	
	@GetMapping("/{userno}")
	@ApiOperation(value = "회원 상세정보", notes = "회원의 정보를 보여준다.(개인정보, 스터디)") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<UserInfoStudyListRes> userDetail(
			@PathVariable("userno") @ApiParam(value = "확인할 회원의 userno", required = true) int userno){
		List<Study> studyList = studyService.getStudyList(userno);
		User user = userService.getUserByUserno(userno);
		System.out.println(user.toString());
		System.out.println(studyList.toString());
		
		return ResponseEntity.status(200).body(UserInfoStudyListRes.of(user, studyList));
	}
	
	@PutMapping("/{userno}")
	@ApiOperation(value = "회원정보 수정", notes = "회원의 정보를 수정한다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<? extends BaseResponseBody> modifyUser(
			@PathVariable("userno") @ApiParam(value = "수정할 회원의 userno", required = true) int userno, 
			@RequestBody UserRegisterPostReq modifyInfo){
		System.out.println(modifyInfo.toString());
		User user = userService.modifyUser(userno, modifyInfo);
		System.out.println("Dddss");
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
}
