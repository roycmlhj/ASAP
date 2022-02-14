package com.ssafy.db.repository;

import java.util.List;
import java.util.Optional;

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
	
	@Override
	public Optional<List<UserHomework>> findUserHomeworkByuserno(int userno) {
		List<UserHomework> userHomeworkList = jpaQueryFactory.select(qUserHomework).from(qUserHomework)
				.where(qUserHomework.userno.eq(userno))
				.fetch();
		return Optional.ofNullable(userHomeworkList);
	}
	
	@Override
	@Transactional
	public void deleteByIdNUserno(Integer homeworkno, int userno) {
		jpaQueryFactory.delete(qUserHomework)
		.where(qUserHomework.homeworkno.eq(homeworkno).and(qUserHomework.userno.eq(userno)))
		.execute();
	}

	@Override
	public int DoHomeworkCnt(int userno) {		
		List<UserHomework> userHomeworkList = jpaQueryFactory.select(qUserHomework).from(qUserHomework)
		.where(qUserHomework.userno.eq(userno).and(qUserHomework.isDone.eq(1)))
		.fetch();

		if(!Optional.ofNullable(userHomeworkList).isPresent())
			return 0;
		
		return userHomeworkList.size();
	}
}
