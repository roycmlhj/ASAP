package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("SchedulePutReqest")
public class SchedulePutReq {
	@ApiModelProperty(name = "schedule pk", example = "1")
	int scheduleno;
	@ApiModelProperty(name = "스케줄 일정", example = "2022/03/13/20/00")
	String nextDate;
}
