package com.ssafy.db.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.QStudy;
import com.ssafy.db.entity.QStudyMember;
import com.ssafy.db.entity.Study;

public class StudyRepositoryCustomImpl extends QuerydslRepositorySupport implements StudyRepositoryCustom {
	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	
	QStudy qStudy = QStudy.study;
	QStudyMember qStudyMember = QStudyMember.studyMember;
	
	public StudyRepositoryCustomImpl() {
		super(Study.class);
	}

//	@Override
//	public Optional<List<Study>> findByUserno(int userno) {
//		System.out.println("에레에레");
//		List<Study> studyList = jpaQueryFactory.select(qStudy).from(qStudy)
//				.innerJoin(qStudy.studyMemberList, qStudyMember)
//				.where(qStudyMember.userno.eq(userno)).fetch();
//	
//		if(studyList == null) {
//			return Optional.empty();
//		}
//		return Optional.ofNullable(studyList);
//	}
	
	@Override
	public List<Study> findByUserno(int userno) {
//		System.out.println("에레에레");
//		List<Study> studyList = jpaQueryFactory.select(qStudy).from(qStudy)
//				.innerJoin(qStudy.studyMemberList, qStudyMember)
//				.where(qStudyMember.userno.eq(userno)).fetch();
//	
//		return studyList;
		return null;
	}
}
