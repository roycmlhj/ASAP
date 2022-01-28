package com.ssafy.db.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.Homework;
import com.ssafy.db.entity.QHomework;
import com.ssafy.db.entity.QUserHomework;

public class HomeworkRepositoryCustomImpl extends QuerydslRepositorySupport implements HomeworkRepositoryCustom {
	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	QHomework qHomework = QHomework.homework;
	QUserHomework qUserHomework = QUserHomework.userHomework;
	
	public HomeworkRepositoryCustomImpl() {
		super(Homework.class);
	}

	@Override
	public Optional<List<Homework>> findByStudyno(int studyno) {
		List<Homework> homeworkList = jpaQueryFactory.selectFrom(qHomework)
				.where(qHomework.studyno.eq(studyno)).fetch();
		return Optional.ofNullable(homeworkList);
	}
	
	// add
	@Override
	public Optional<List<Homework>> findHomeworkByuserno(int userno, int flag) {
//		select * from homework
//		where homeworkno = 
//			(select homeworkno from user_homework 
//					where userno = {userno})
		List<Homework> homeworkList;
		if(flag == 2) {
			homeworkList = jpaQueryFactory.selectFrom(qHomework)
					.where(qHomework.homeworkno.eq(
							JPAExpressions.select(qUserHomework.homeworkno)
							.from(qUserHomework)
							.where(qUserHomework.userno.eq(userno))))
					.fetch();
		}else {
			homeworkList = jpaQueryFactory.selectFrom(qHomework)
					.where(qHomework.homeworkno.eq(
							JPAExpressions.select(qUserHomework.homeworkno)
							.from(qUserHomework)
							.where(qUserHomework.userno.eq(userno).and(qUserHomework.isDone.eq(flag)))))
					.fetch();
		}
		return Optional.ofNullable(homeworkList);
	}

}