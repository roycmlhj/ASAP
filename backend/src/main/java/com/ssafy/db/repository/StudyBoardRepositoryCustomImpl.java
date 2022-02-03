package com.ssafy.db.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.StudyBoard;

public class StudyBoardRepositoryCustomImpl extends QuerydslRepositorySupport  implements StudyBoardRepositoryCustom {
	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	
	
	public StudyBoardRepositoryCustomImpl() {
		super(StudyBoard.class);
	}

}
