package com.ssafy.api.request;

import java.util.List;

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
	@ApiModelProperty(name = "스터디 생성 유저 pk ", example = "1")
	int userno;
	@ApiModelProperty(name = "스터디 소분류", example = "['알고리즘', '백준', '프로그래머스']")
	List<String> interests;
}
