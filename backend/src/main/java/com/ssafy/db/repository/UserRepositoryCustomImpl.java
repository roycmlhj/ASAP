package com.ssafy.db.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.api.response.BoardMember;
import com.ssafy.db.entity.QStudyMember;
import com.ssafy.db.entity.QUser;

public class UserRepositoryCustomImpl implements UserRepositoryCustom {
	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	QUser qUser = QUser.user;
	QStudyMember qStudyMember = QStudyMember.studyMember;
	
	@Override
	public Optional<List<BoardMember>> findBoardMemberByStudyno(int studyno) {
		List<Tuple> boardMember = jpaQueryFactory.select(qUser.userno, qUser.nickname, qStudyMember.position, qUser.image)
				.from(qUser).join(qStudyMember).on(qUser.userno.eq(qStudyMember.userno))
				.where(qStudyMember.studyno.eq(studyno))
				.fetch();
		
		List<BoardMember> boardMemberList = new ArrayList<BoardMember>(); 
		for (Tuple tuple : boardMember) {
			BoardMember b = new BoardMember(
					tuple.get(qUser.userno),
					tuple.get(qUser.nickname),
					tuple.get(qStudyMember.position),
					tuple.get(qUser.image)
			);
			boardMemberList.add(b);
		}
		return Optional.ofNullable(boardMemberList);
	}

	@Override
	public String findNicknameByUserno(int userno) {
		String nickname = jpaQueryFactory.select(qUser.nickname).from(qUser)
				.where(qUser.userno.eq(userno)).fetchOne();
		return nickname;
	}

}
