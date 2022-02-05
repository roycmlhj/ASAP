package com.ssafy.api.request;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("StudyPutReqest")
public class StudyPutReq {	
	@ApiModelProperty(name = "study pk", example = "1")
	int studyno;
	@ApiModelProperty(name = "카테고리", example = "IT")
	String category;
	@ApiModelProperty(name = "관심분야 리스트", example = "['알고리즘', '백준', '프로그래머스']")
	List<String> interests;
	@ApiModelProperty(name = "스터디 설명", example = "알고리즘을 뿌셔버리는 스터디 모임 입니다.")
	String description;
	@ApiModelProperty(name = "스터디 최대 인원", example = "6")
	int memberno;
}
