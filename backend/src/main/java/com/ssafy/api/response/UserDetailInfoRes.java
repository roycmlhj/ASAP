package com.ssafy.api.response;

import java.util.List;

import com.ssafy.db.entity.Homework;
import com.ssafy.db.entity.Study;
import com.ssafy.db.entity.User;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserDetailInfoResponse")
public class UserDetailInfoRes {
	// study analyze 추가 필요.
	@ApiModelProperty(name = "유저 정보")
	User user;
	@ApiModelProperty(name = "참여 스터디 목록")
	List<Study> studyList;
	@ApiModelProperty(name = "진행 중 과제 목록")
	List<Homework> onHomeworkList;
	@ApiModelProperty(name = "완료 과제 목록")
	List<Homework> doneHomeworkList;
	
	public static UserDetailInfoRes of(User user, List<Study> studyList, List<Homework> onHomeworkList, List<Homework> doneHomeworkList) {
		UserDetailInfoRes res = new UserDetailInfoRes();
		res.user = user;
		res.studyList = studyList;
		res.onHomeworkList = onHomeworkList;
		res.doneHomeworkList = doneHomeworkList;
		return res;
	}
}
