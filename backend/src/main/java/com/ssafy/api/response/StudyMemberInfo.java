package com.ssafy.api.response;

import com.ssafy.db.entity.StudyMember;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudyMemberInfo {
	StudyMember studyMember;
    String nickname;
    String image;
    
	public StudyMemberInfo() {
		super();
	}
	
	public StudyMemberInfo(StudyMember studyMember, String nickname, String image) {
		this.studyMember = studyMember;
		this.nickname = nickname;
		this.image = image;
	}
}
