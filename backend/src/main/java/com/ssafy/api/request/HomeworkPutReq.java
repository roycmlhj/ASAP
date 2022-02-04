package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("HomeworkPutReqest")
public class HomeworkPutReq {
	@ApiModelProperty(name = "homework pk", example = "1")
	int homeworkno;
	@ApiModelProperty(name = "과제 제목", example = "백준 432141번 문제 풀기.")
	String title;
	@ApiModelProperty(name = "과제 내용", example = "어쩌구저쩌구 방식으로 풀어오기로 하자.")
	String content;
	@ApiModelProperty(name = "마감 기일", example = "2022/04/03/20/30")
	String endDate;
}
