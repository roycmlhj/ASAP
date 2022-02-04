package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("ScheduleCreatePostReqest")
public class ScheduleCreatePostReq {
	@ApiModelProperty(name = "study pk", example = "1")
	int studyno;
	@ApiModelProperty(name = "스케줄 일정", example = "2022/03/13/20/00")
	String nextDate;
}
