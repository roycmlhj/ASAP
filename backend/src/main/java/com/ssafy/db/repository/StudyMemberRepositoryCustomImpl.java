package com.ssafy.db.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.api.request.StudyAcceptPutReq;
import com.ssafy.db.entity.QStudyMember;
import com.ssafy.db.entity.StudyMember;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StudyMemberRepositoryCustomImpl extends QuerydslRepositorySupport implements StudyMemberRepositoryCustom {

	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	QStudyMember qStudyMember = QStudyMember.studyMember;
	
	public StudyMemberRepositoryCustomImpl() {
		super(StudyMember.class);
	}
 
 private static final Logger log = LoggerFactory.getLogger(StudyMemberRepositoryCustomImpl.class);

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

	@Override
	@Transactional
	public void kickStudyMember(int userno, int studyno) {
		jpaQueryFactory.delete(qStudyMember)
		.where(qStudyMember.userno.eq(userno)
				.and(qStudyMember.studyno.eq(studyno)))
		.execute();
	}
	
	@Override
	public StudyMember findByUsernoNStudyNo(int userno, int studyno) {
    log.info("check3 studyno : {}" + studyno);
		StudyMember studyMember = jpaQueryFactory.select(qStudyMember).from(qStudyMember)
				.where(qStudyMember.userno.eq(userno).
						and(qStudyMember.studyno.eq(studyno)))
				.fetchOne();
		return studyMember;
	}

	//나중에는 스터디 참여도가 높은 사람한테 위임될수 있게 바꾸자
	@Override
	public Optional<StudyMember> findByStudynoMandate(int studyno) {
		StudyMember studyMember = jpaQueryFactory.selectFrom(qStudyMember)
				.where(qStudyMember.studyno.eq(studyno)
						.and(qStudyMember.position.eq(1)))
				.fetchFirst();
		return Optional.ofNullable(studyMember);
	}

	@Override
	@Transactional
	public void studyLeaderMandate(int userno, int studyno) {
		jpaQueryFactory.update(qStudyMember)
		.set(qStudyMember.position, 0)
		.execute();
	}

	@Override
	@Transactional
	public void addTime(int userno, int studyno, String time) {
		jpaQueryFactory.update(qStudyMember)
		.set(qStudyMember.studyTime, time)
		.where(qStudyMember.studyno.eq(studyno).and(qStudyMember.userno.eq(userno)))
		.execute();
	}

	@Override
	public List<Integer> findStudynobyuserno(int userno) {
		List<Integer> list = jpaQueryFactory.select(qStudyMember.studyno).from(qStudyMember)
				.where(qStudyMember.userno.eq(userno))
				.fetch();
		return list;
	}
}
