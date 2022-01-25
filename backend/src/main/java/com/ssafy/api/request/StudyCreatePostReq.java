package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("StudyCreatePostRequest")
public class StudyCreatePostReq {
	@ApiModelProperty(name = "스터디 이름", example = "알고리즘 뿌수기 스터디")
	String studyname;
	@ApiModelProperty(name = "스터디 대분류", example = "IT")
	String category;
	@ApiModelProperty(name = "스터디 설명", example = "알고리즘을 뿌셔버리는 스터디 모임 입니다.")
	String description;
	@ApiModelProperty(name = "스터디 최대 인원", example = "6")
	int memberno;
	@ApiModelProperty(name = "스터디 생성 회원 ", example = "ssafy3@ssafy.com")
	String maker;
}
