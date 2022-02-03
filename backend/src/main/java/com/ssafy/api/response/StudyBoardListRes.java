package com.ssafy.api.response;

import java.util.List;

import com.ssafy.db.entity.StudyBoard;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("StudyBoardListResponse")
public class StudyBoardListRes {
	@ApiModelProperty(name = "스터디 게시글 리스트")
	List<StudyBoard> studyBoardList;
	
	public static StudyBoardListRes of(List<StudyBoard> studyBoardList) {
		StudyBoardListRes res = new StudyBoardListRes();
		res.studyBoardList = studyBoardList;
		return res;
	}
}
