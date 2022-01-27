package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("StudyApplyPostRequest")
public class StudyApplyPostReq {
	@ApiModelProperty(name = "스터디 pk", example = "1")
	int studyno;
	@ApiModelProperty(name = "유저 pk", example = "1")
	int userno;
}
