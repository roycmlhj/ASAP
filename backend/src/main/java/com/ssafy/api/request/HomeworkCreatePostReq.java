package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("HomeworkCreatePostRequest")
public class HomeworkCreatePostReq {
	@ApiModelProperty(name = "study pk", example = "1")
	int studyno;
	@ApiModelProperty(name = "user pk", example = "1")
	int userno;
	@ApiModelProperty(name = "마감일", example = "2022/03/24/19/30")
	String endDate;
	@ApiModelProperty(name = "과제 제목", example = "백준2341231번 풀기")
	String title;
	@ApiModelProperty(name = "과제 내용", example = "어쩌구저쩌구 형식으로 풀어보자.")
	String content;
}
