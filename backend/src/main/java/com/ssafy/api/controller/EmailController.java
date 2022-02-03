package com.ssafy.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.api.service.EmailService;
import com.ssafy.api.service.EmailServiceImpl;
import com.ssafy.common.model.response.BaseResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "이메일 API", tags = {"Email"})
@RestController
@RequestMapping("/api/v1/email")
public class EmailController {
	@Autowired
	EmailService emailService;
	
	@GetMapping("/{email}")
	@ApiOperation(value = "회원 가입시 이메인 인증번호 전송", notes = "기존 사용하고 있는 이메일에 인증번호 전송")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<? extends BaseResponseBody> emailAuth(
			@PathVariable @ApiParam(value="이메일 정보", required = true) String email) throws Exception{
		String confirm = emailService.sendSimpleMessage(email);
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
	
	@PostMapping("email-confirm")
	@ApiOperation(value = "전송된 이메일 인증번호 인증", notes = "기존사용하고 있는 이메일을 통해 인증")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<? extends BaseResponseBody> emailConfirm(
			@RequestBody @ApiParam(value="인증번호", required = true) String code) throws Exception{
		code = code.substring(0,code.length()-1);
		if(EmailServiceImpl.ePw.equals(code)) {
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
		}else {
			return ResponseEntity.status(401).body(BaseResponseBody.of(401, "인증 실패"));
		}
	}
}
