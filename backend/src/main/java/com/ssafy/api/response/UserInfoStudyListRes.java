package com.ssafy.api.response;

import java.util.List;

import com.ssafy.db.entity.Study;
import com.ssafy.db.entity.User;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserInfoStudyListResponse")
public class UserInfoStudyListRes {
	@ApiModelProperty(name = "유저 정보")
	User user;
	@ApiModelProperty(name = "참여 스터디 목록")
	List<Study> studyList;
	
	public static UserInfoStudyListRes of(User user, List<Study> studyList) {
		UserInfoStudyListRes res = new UserInfoStudyListRes();
		res.user = user;
		res.studyList = studyList;
		return res;
	}
}
