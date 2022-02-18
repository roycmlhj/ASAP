package com.ssafy.db.repository;

import java.util.List;
import java.util.Optional;

import com.ssafy.api.request.FileSavePostReq;
import com.ssafy.db.entity.UserHomework;

public interface UserHomeworkRepositoryCustom {
	void deleteUserHomework(int homeworkno);
	Optional<List<UserHomework>> findUserHomeworkByuserno(int userno);
	void deleteByIdNUserno(Integer homeworkno, int userno);
	int DoHomeworkCnt(int userno);
}
