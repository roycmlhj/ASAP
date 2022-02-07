package com.ssafy.api.response;

import java.util.List;

import com.ssafy.db.entity.Study;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("StudyResponse")
public class StudyRes {
	@ApiModelProperty(name = "스터디 정보")
	Study study;
	
	public static StudyRes of(Study study) {
		StudyRes res = new StudyRes();
		res.study = study;
		return res;
	}
}
