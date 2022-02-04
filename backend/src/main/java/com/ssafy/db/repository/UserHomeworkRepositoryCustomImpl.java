package com.ssafy.db.repository;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.QUserHomework;
import com.ssafy.db.entity.UserHomework;

public class UserHomeworkRepositoryCustomImpl extends QuerydslRepositorySupport  implements UserHomeworkRepositoryCustom {
	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	QUserHomework qUserHomework = QUserHomework.userHomework;
	
	
	public UserHomeworkRepositoryCustomImpl() {
		super(UserHomework.class);
	}

	@Override
	@Transactional
	public void deleteUserHomework(int homeworkno) {
		jpaQueryFactory.delete(qUserHomework)
		.where(qUserHomework.homeworkno.eq(homeworkno))
		.execute();
	}

}
