package com.ssafy.db.repository;

import java.util.List;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.QStudy;
import com.ssafy.db.entity.QStudyMember;
import com.ssafy.db.entity.Study;

public class StudyRepositoryCustomImpl implements StudyRepositoryCustom {
	private JPAQueryFactory jpaQueryFactory;
	QStudy qStudy = QStudy.study;
	QStudyMember qStudyMember = QStudyMember.studyMember;

	@Override
	public List<Study> findByUserno(int userno) {
		List<Study> studyList = jpaQueryFactory.select(qStudy).from(qStudy)
				.innerJoin(qStudy.studyMemberList, qStudyMember)
				.where(qStudyMember.userno.eq(userno)).fetch();
				
		return studyList;
	}

}
