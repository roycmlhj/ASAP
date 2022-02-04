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
	List<StudyBoardNNickname> studyBoardList;
	
	public static StudyBoardListRes of(List<StudyBoardNNickname> studyBoardNNicknameList) {
		StudyBoardListRes res = new StudyBoardListRes();
//		for(int i = 0; i < studyBoardList.size(); i++) {
//			System.out.println(i + " : " + studyBoardList.get(i).toString() + " " + nicknameList.get(i));
//			res.studyBoardList.add(new StudyBoardNNickname(studyBoardList.get(i), nicknameList.get(i)));
//			System.out.println("check");
//		}
		res.studyBoardList = studyBoardNNicknameList;
		return res;
	}
}
