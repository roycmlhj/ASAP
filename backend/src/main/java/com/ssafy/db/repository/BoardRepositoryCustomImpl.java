package com.ssafy.db.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.api.response.BoardMember;
import com.ssafy.db.entity.Board;

public class BoardRepositoryCustomImpl extends QuerydslRepositorySupport implements BoardRepositoryCustom {

	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	
	public BoardRepositoryCustomImpl() {
		super(Board.class);
	}
}
