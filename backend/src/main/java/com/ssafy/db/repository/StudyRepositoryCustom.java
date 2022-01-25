package com.ssafy.db.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafy.db.entity.Study;

@Repository
public interface StudyRepositoryCustom {
	List<Study> findByUserno(int userno);
}
