package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("StudyBoardPutReqest")
public class StudyBoardPutReq {
	@ApiModelProperty(name = "board pk", example = "1")
	int boardno;
	@ApiModelProperty(name = "게시글 제목", example = "수정할 게시글 제목입니다.")
	String title;
	@ApiModelProperty(name = "게시글 내용", example = "수정할 게시글 내용입니다.")
	String content;
}
