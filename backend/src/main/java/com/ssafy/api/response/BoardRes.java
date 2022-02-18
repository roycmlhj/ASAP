package com.ssafy.api.response;

import java.util.List;

import com.ssafy.db.entity.Board;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("BoardResponse")
public class BoardRes {
	@ApiModelProperty(name = "Board entity")
	Board board;
	int memberCnt;
	List<BoardMember> list;
	
	public static BoardRes of(Board board, List<BoardMember> list, int memberCnt) {
		BoardRes res = new BoardRes();
		res.board = board;
		res.list = list;
		res.memberCnt = memberCnt;
		return res;
	}
}
