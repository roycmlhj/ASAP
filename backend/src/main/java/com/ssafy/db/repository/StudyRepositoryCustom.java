package com.ssafy.db.repository;

import java.util.List;

import com.ssafy.db.entity.Study;

public interface StudyRepositoryCustom {
	List<Study> findByUserno(int userno);
}
