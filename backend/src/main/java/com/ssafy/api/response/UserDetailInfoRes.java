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
	@ApiModelProperty(name = "유저 정보")
	User user;
	@ApiModelProperty(name = "참여 스터디 목록")
	List<Study> studyList;
	@ApiModelProperty(name = "진행 중 과제 목록")
	List<HomeworkNStudy> onHomeworkList;
	@ApiModelProperty(name = "완료 과제 목록")
	List<HomeworkNStudy> doneHomeworkList;
	@ApiModelProperty(name = "스터디 시간 분석")
	StudyAnalyze study_analyze;

	
	public static UserDetailInfoRes of(User user, List<Study> studyList, List<HomeworkNStudy> onHomeworkList, List<HomeworkNStudy> doneHomeworkList, StudyAnalyze study_analyze) {
		UserDetailInfoRes res = new UserDetailInfoRes();
		res.user = user;
		res.studyList = studyList;
		res.onHomeworkList = onHomeworkList;
		res.doneHomeworkList = doneHomeworkList;
		res.study_analyze = study_analyze;
		return res;
	}
}
