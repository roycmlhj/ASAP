package com.ssafy.db.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.QStudy;
import com.ssafy.db.entity.QStudyMember;
import com.ssafy.db.entity.Study;
import com.ssafy.db.entity.StudyMember;

public class StudyRepositoryCustomImpl extends QuerydslRepositorySupport implements StudyRepositoryCustom {

	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	QStudy qStudy = QStudy.study;
	QStudyMember qStudyMember = QStudyMember.studyMember;

	public StudyRepositoryCustomImpl() {
		super(Study.class);
	}

	@Override
	public List<Study> findByUserno(int userno) {
		/*
		 * select a.studyno, a.name
		 * from study a
		 * INNER JOIN
		 * study_member b
		 * ON a.studyno = b.studyno
		 * where b.userno = {userno};
		 * 
		 */

		List<Study> studyList = jpaQueryFactory.select(qStudy).from(qStudy)
		.innerJoin(qStudy.studyMemberList, qStudyMember)
		.where(qStudyMember.userno.eq(userno).and(qStudyMember.position.ne(2)))
		.fetch();
		
		return studyList;
	}
}
