package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("StudyBoardCreatePostRequest")
public class StudyBoardCreatePostReq {
	@ApiModelProperty(name = "게시글 종류 0:공지글, 1:일반글", example = "0")
	int type;
	@ApiModelProperty(name = "스터디 pk", example = "1")
	int studyno;
	@ApiModelProperty(name = "작성 유저 pk", example = "1")
	int userno;
	@ApiModelProperty(name = "게시글 제목", example = "필독 공지 스터디 규칙!")
	String title;
	@ApiModelProperty(name = "게시글 내용", example = "규칙 1. 과제는 항상 한다. 규칙 2...")
	String content;
}
