package com.ssafy.api.response;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudyAnalyze {
	int total_time;
	List<StudyTime> studyTime = new ArrayList<StudyTime>();
	
	
	public void addStudyTime(StudyTime studytimeadd) {
		studyTime.add(studytimeadd);
	}
}
