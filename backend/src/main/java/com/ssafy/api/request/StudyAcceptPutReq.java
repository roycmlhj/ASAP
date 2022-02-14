package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("StudyAcceptPutReqest")
public class StudyAcceptPutReq {
	@ApiModelProperty(name = "스터디 pk", example = "1")
	int studyno;
	@ApiModelProperty(name = "유저 pk", example = "1")
	int userno;
	@ApiModelProperty(name = "가입 수락 여부", example = "1")
	int flag;
}
