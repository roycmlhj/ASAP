package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("AddTimeRequest")
public class AddTimeReq {
	@ApiModelProperty(name = "스터디 번호")
	int studyno;
	@ApiModelProperty(name = "작성자 번호")
	int userno;
	@ApiModelProperty(name = "참여 시간")
	String time;
}
