package com.ssafy.api.response;

import java.util.List;

import com.ssafy.db.entity.Study;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("StudyMemberListResponse")
public class StudyMemberListRes {
	@ApiModelProperty(name = "스터디 멤버 관련 정보")
	List<StudyMemberInfo> studyMemberList;
	
	public static StudyMemberListRes of(List<StudyMemberInfo> studyMemberList) {
		StudyMemberListRes res = new StudyMemberListRes();
		res.studyMemberList = studyMemberList;
		return res;
	}
}
