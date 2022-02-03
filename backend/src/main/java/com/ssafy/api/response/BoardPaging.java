package com.ssafy.api.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardPaging {
	int boardno;
	String boardname;
	int userno;
	String nickname;
	String category;
	int is_recruit;
	int maxmember;
	int membercnt;
	String timestamp;
	
	public BoardPaging(int boardno, String boardname, int userno, String nickname, String category, int is_recruit,
			int maxmember, int membercnt, String timestamp) {
		this.boardno = boardno;
		this.boardname = boardname;
		this.userno = userno;
		this.nickname = nickname;
		this.category = category;
		this.is_recruit = is_recruit;
		this.maxmember = maxmember;
		this.membercnt = membercnt;
		this.timestamp = timestamp;
	}
}
