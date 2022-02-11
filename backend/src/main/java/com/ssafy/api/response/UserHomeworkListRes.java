package com.ssafy.api.response;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserHomeworkListResponse")
public class UserHomeworkListRes {
	@ApiModelProperty(name = "user homework 정보")
	List<UserHomeworkInfo> userHomeworkInfoList;
	
	public static UserHomeworkListRes of(List<UserHomeworkInfo> userHomeworkInfoList) {
		UserHomeworkListRes res = new UserHomeworkListRes();
		res.userHomeworkInfoList = userHomeworkInfoList;
		return res;
	}
}
