package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserKickPostRequest")
public class UserKickPostReq {
	@ApiModelProperty(name = "유저 no")
	int userno;
	@ApiModelProperty(name = "스터디 no")
	int studyno;
}
