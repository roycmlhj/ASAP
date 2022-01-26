package com.ssafy.db.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafy.db.entity.Homework;

@Repository
public interface HomeworkRepositoryCustom {
	List<Homework> findByStudyno(int studyno);
}
