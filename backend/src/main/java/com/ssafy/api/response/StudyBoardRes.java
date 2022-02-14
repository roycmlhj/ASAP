package com.ssafy.api.response;

import java.util.List;

import com.ssafy.db.entity.StudyBoard;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("StudyBoardResponse")
public class StudyBoardRes {
	@ApiModelProperty(name = "스터디 게시글")
	StudyBoard studyBoard;
	@ApiModelProperty(name = "작성자 닉네임")
	String nickname;
	
	public static StudyBoardRes of(StudyBoard studyBoard, String nickname) {
		StudyBoardRes res = new StudyBoardRes();
		res.studyBoard = studyBoard;
		res.nickname = nickname;
		return res;
	}
}
