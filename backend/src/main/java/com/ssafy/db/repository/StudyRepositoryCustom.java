package com.ssafy.db.repository;

import java.util.List;
import java.util.Optional;

import com.ssafy.db.entity.Study;

public interface StudyRepositoryCustom {
	List<Study> findByUserno(int userno);
}
