package com.ssafy.api.response;

import java.util.List;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;

import com.ssafy.db.entity.QStudy;
import com.ssafy.db.entity.Study;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("StudyListResponse")
public class StudyListRes {
	@ApiModelProperty(name = "참가 스터디 목록")
	List<Study> studyList;
	
	public static StudyListRes of(List<Study> studyList) {
		StudyListRes res = new StudyListRes();
		res.studyList = studyList;
		return res;
	}
}
