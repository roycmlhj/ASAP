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

import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.api.response.UserInfoStudyListRes;
import com.ssafy.api.response.UserListRes;
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
		
		return ResponseEntity.status(200).body(UserInfoStudyListRes.of(user, studyList));
	}
	
	@PostMapping("/{userno}")
	@ApiOperation(value = "회원정보 수정", notes = "회원의 정보를 수정한다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<? extends BaseResponseBody> modifyUser(
			@RequestBody @ApiParam(value = "회원 수정정보", required = false) UserRegisterPostReq modifyInfo,
			@PathVariable("userno") @ApiParam(value = "수정할 회원의 userno", required = true) int userno){ 
		User user = userService.modifyUser(userno, modifyInfo);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
	
	//유저 탈퇴 - delflag 1로 변경
	//studymember에서 모두삭제
	//만약 position이 0이면 그 사람이 가입한 스터디 중 maker가 userno인 스터디의 방장이라면
	//그 스터디의 방장은 그 스터디에 가입한 사람들 중 가장 오래 된 유저가 된다.
	//만약 스터디에 가입한 사람이 더 이상 없다면 그 스터디는 없어진다.
	@DeleteMapping("/{userno}")
	@ApiOperation(value = "회원탈퇴", notes = "회원 탈퇴한다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<? extends BaseResponseBody> deleteUser(
			@PathVariable("userno") @ApiParam(value = "탈퇴할 회원의 userno", required = true) int userno){
		userService.deleteUser(userno);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
	
	@GetMapping("/userlist")
	@ApiOperation(value = "전체 회원 목록", notes = "전체 회원 목록을 불러온다") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<UserListRes> userList(){
		List<User> userlist = userService.getUserList();
		
		return ResponseEntity.status(200).body(UserListRes.of(userlist));
	}
}
