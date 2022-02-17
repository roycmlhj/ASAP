package com.ssafy.db.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.Board;
import com.ssafy.db.entity.QBoard;

public class BoardRepositoryCustomImpl extends QuerydslRepositorySupport implements BoardRepositoryCustom {

	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	QBoard qBoard = QBoard.board;
	
	public BoardRepositoryCustomImpl() {
		super(Board.class);
	}
	
	@Override
	public List<Integer> findBoardnoByUserno(int userno) {
		List<Integer> list = jpaQueryFactory.select(qBoard.boardno).from(qBoard)
				.where(qBoard.userno.eq(userno))
				.fetch();
		return list;
	}
}
