package com.ssafy.db.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.Homework;
import com.ssafy.db.entity.QHomework;

public class HomeworkRepositoryCustomImpl extends QuerydslRepositorySupport implements HomeworkRepositoryCustom {
	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	QHomework qHomework = QHomework.homework;
	
	public HomeworkRepositoryCustomImpl() {
		super(Homework.class);
	}

	@Override
	public Optional<List<Homework>> findByStudyno(int studyno) {
		List<Homework> homeworkList = jpaQueryFactory.selectFrom(qHomework)
				.where(qHomework.studyno.eq(studyno)).fetch();
		return Optional.ofNullable(homeworkList);
	}

}
