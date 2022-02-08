package com.ssafy.db.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.api.request.HomeworkPutReq;
import com.ssafy.db.entity.Homework;
import com.ssafy.db.entity.QHomework;
import com.ssafy.db.entity.QUserHomework;
import com.ssafy.db.entity.UserHomework;

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
	
	@Override
	@Transactional
	public void modifyHomework(HomeworkPutReq homeworkPutInfo) {
		jpaQueryFactory.update(qHomework)
		.set(qHomework.title, homeworkPutInfo.getTitle())
		.set(qHomework.content, homeworkPutInfo.getContent())
		.set(qHomework.endDate, homeworkPutInfo.getEndDate())
		.where(qHomework.homeworkno.eq(homeworkPutInfo.getHomeworkno()))
		.execute();
	}

	@Override
	@Transactional
	public void deleteHomework(int homeworkno) {
		jpaQueryFactory.delete(qHomework)
		.where(qHomework.homeworkno.eq(homeworkno))
		.execute();	
	}

}
