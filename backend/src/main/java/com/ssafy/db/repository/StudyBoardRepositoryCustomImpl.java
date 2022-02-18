package com.ssafy.db.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.api.request.StudyBoardPutReq;
import com.ssafy.db.entity.QStudyBoard;
import com.ssafy.db.entity.StudyBoard;

public class StudyBoardRepositoryCustomImpl extends QuerydslRepositorySupport  implements StudyBoardRepositoryCustom {
	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	QStudyBoard qStudyBoard = QStudyBoard.studyBoard;
	
	
	public StudyBoardRepositoryCustomImpl() {
		super(StudyBoard.class);
	}


	@Override
	@Transactional
	public void modifyStudyBoard(StudyBoardPutReq studyBoardPutInfo) {
		jpaQueryFactory.update(qStudyBoard)
		.set(qStudyBoard.title, studyBoardPutInfo.getTitle())
		.set(qStudyBoard.content, studyBoardPutInfo.getContent())
		.where(qStudyBoard.boardno.eq(studyBoardPutInfo.getBoardno()))
		.execute();
	}


	@Override
	@Transactional
	public void deleteStudyBoard(int boardno) {
		jpaQueryFactory.delete(qStudyBoard)
		.where(qStudyBoard.boardno.eq(boardno))
		.execute();
	}


	@Override
	public Optional<List<StudyBoard>> findStudyBoardByStudyno(int studyno) {
		List<StudyBoard> list = jpaQueryFactory.select(qStudyBoard).from(qStudyBoard)
				.where(qStudyBoard.studyno.eq(studyno))
				.fetch();
		return Optional.ofNullable(list);
	}

}
