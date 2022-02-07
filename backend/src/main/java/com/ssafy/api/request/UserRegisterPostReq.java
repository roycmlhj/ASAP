package com.ssafy.api.request;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 유저 회원가입 API ([POST] /api/v1/user/signup) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("UserRegisterPostRequest")
public class UserRegisterPostReq {
	@ApiModelProperty(name="유저 Email", example="ssafy@naver.com")
	String email;
	@ApiModelProperty(name="유저 Password", example="12345")
	String password;
	@ApiModelProperty(name="유저 NickName", example="홍길동")
	String nickname;
	@ApiModelProperty(name="유저 주 관심사", example="사회공헌/교류")
	String mainCategory;
	@ApiModelProperty(name="유저 세부 관심사", example="[공부,요리]")
	List<String> interests;
}
