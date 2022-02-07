package com.ssafy.db.repository;

public interface UserHomeworkRepositoryCustom {
	void deleteUserHomework(int homeworkno);
	void deleteByIdNUserno(Integer homeworkno, int userno);
}
