package com.ssafy.api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.api.service.StudyService;
import com.ssafy.api.service.UserService;
import com.ssafy.common.model.response.BaseResponseBody;

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
	
	@GetMapping("{userno}")
	@ApiOperation(value = "회원 상세정보", notes = "회원의 정보를 보여준다.(개인정보, 스터디)") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<? extends BaseResponseBody> userDetail(
			@PathVariable("userno") @ApiParam(value = "확인할 회원의 userno", required = true) int userno){
		return 
	}
	
}
