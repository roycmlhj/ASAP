package com.ssafy.api.response;

import java.util.List;

import com.ssafy.db.entity.Study;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("StudyInfoListResponse")
public class StudyInfoListRes {
	@ApiModelProperty(name = "모든 참가 스터디 정보")
	List<StudyInfo> studyInfoList;

//	public static StudyListRes of(List<Study> studyList) {
//		StudyListRes res = new StudyListRes();
//		res.studyList = studyList;
//		return res;
//	}
}
