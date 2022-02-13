package com.ssafy.api.response;

import java.util.List;

import com.ssafy.db.entity.Homework;
import com.ssafy.db.entity.Schedule;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("HomeworkNScheduleGetResponse")
public class HomeworkNScheduleGetRes {
	@ApiModelProperty(name = "스터디 과제 리스트")
	List<Homework> homeworkList;
	@ApiModelProperty(name = "스터디 일정 리스트")
	List<Schedule> scheduleList;
	
	public static HomeworkNScheduleGetRes of(List<Homework> homeworkList, List<Schedule> scheduleList) {
		HomeworkNScheduleGetRes res = new HomeworkNScheduleGetRes();
		res.homeworkList = homeworkList;
		res.scheduleList = scheduleList;
		return res;
	}
}
