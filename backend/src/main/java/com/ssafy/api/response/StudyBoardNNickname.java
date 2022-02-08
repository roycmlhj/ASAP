package com.ssafy.api.response;

import com.ssafy.db.entity.StudyBoard;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudyBoardNNickname {
	StudyBoard studyBoard;
	String nickname;
	
	public StudyBoardNNickname() {
		super();
	}

	public StudyBoardNNickname(StudyBoard studyBoard, String nickname) {
		this.studyBoard = studyBoard;
		this.nickname = nickname;
	}
	
	
}
