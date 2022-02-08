package com.ssafy.db.repository;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.QFileData;

public class FileRepositoryCustomImpl implements FileRepositoryCustom {
	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	QFileData qFileData = QFileData.fileData;
	
	@Override
	@Transactional
	public long deletebyBoardno(int boardno) {
		return jpaQueryFactory.delete(qFileData)
		.where(qFileData.boardno.eq(boardno))
		.execute();
	}

}
