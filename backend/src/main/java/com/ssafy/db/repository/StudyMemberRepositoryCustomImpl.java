package com.ssafy.db.repository;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.api.request.StudyAcceptPutReq;
import com.ssafy.db.entity.QStudyMember;
import com.ssafy.db.entity.StudyMember;

public class StudyMemberRepositoryCustomImpl extends QuerydslRepositorySupport implements StudyMemberRepositoryCustom {

	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	QStudyMember qStudyMember = QStudyMember.studyMember;
	
	public StudyMemberRepositoryCustomImpl() {
		super(StudyMember.class);
	}

	@Override
	@Transactional
	public void acceptStudy(StudyAcceptPutReq acceptInfo) {
		jpaQueryFactory.update(qStudyMember)
		.set(qStudyMember.position, 1)
		.where(qStudyMember.studyno.eq(acceptInfo.getStudyno()).
				and(qStudyMember.userno.eq(acceptInfo.getUserno())))
		.execute();
	}

	@Override
	@Transactional
	public void rejectStudy(StudyAcceptPutReq acceptInfo) {
		jpaQueryFactory.delete(qStudyMember)
		.where(qStudyMember.studyno.eq(acceptInfo.getStudyno()).
				and(qStudyMember.userno.eq(acceptInfo.getUserno())))
		.execute();
	}

}
