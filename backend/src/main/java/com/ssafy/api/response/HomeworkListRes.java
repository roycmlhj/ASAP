package com.ssafy.api.response;

import java.util.List;

import com.ssafy.db.entity.Homework;
import com.ssafy.db.entity.StudyBoard;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("HomeworkListResponse")
public class HomeworkListRes {
	@ApiModelProperty(name = "과제 글 리스트")
	List<Homework> homeworkList;
	
	public static HomeworkListRes of(List<Homework> homeworkList) {
		HomeworkListRes res = new HomeworkListRes();
		res.homeworkList = homeworkList;
		return res;
	}
}
