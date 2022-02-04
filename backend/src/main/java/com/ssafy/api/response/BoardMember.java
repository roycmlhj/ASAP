package com.ssafy.api.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardMember {
	int userno;
	String nickname;
	int position;
	String image;
	
	public BoardMember(int userno, String nickname, int position, String image) {
		this.userno = userno;
		this.nickname = nickname;
		this.position = position;
		this.image = image;
	}
}
