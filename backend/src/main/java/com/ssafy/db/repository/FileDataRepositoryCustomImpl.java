package com.ssafy.db.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.FileData;
import com.ssafy.db.entity.QFileData;

public class FileDataRepositoryCustomImpl implements FileDataRepositoryCustom {
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

	@Override
	public Optional<FileData> findbyBoardno(int boardno) {
		FileData file = jpaQueryFactory.select(qFileData).from(qFileData)
				.where(qFileData.boardno.eq(boardno))
				.fetchOne();
		return Optional.ofNullable(file);
	}
}
