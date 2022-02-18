package com.ssafy.api.response;

import com.ssafy.db.entity.Homework;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("HomeworkResponse")
public class HomeworkRes {
	@ApiModelProperty(name = "스터디 게시글")
	Homework homework;
	@ApiModelProperty(name = "작성자 닉네임")
	String nickname;
	
	public static HomeworkRes of(Homework homework, String nickname) {
		HomeworkRes res = new HomeworkRes();
		res.homework = homework;
		res.nickname = nickname;
		return res;
	}
}
