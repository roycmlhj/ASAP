package com.ssafy.db.repository;

import java.util.List;
import java.util.Optional;

import com.ssafy.api.response.BoardMember;

public interface UserRepositoryCustom {
	Optional<List<BoardMember>> findBoardMemberByStudyno(int studyno);
	String findNicknameByUserno(int userno);
}
