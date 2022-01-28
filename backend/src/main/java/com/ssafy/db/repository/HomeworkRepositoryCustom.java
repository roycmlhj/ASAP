package com.ssafy.db.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.ssafy.db.entity.Homework;

@Repository
public interface HomeworkRepositoryCustom {
	Optional<List<Homework>> findByStudyno(int studyno);
}
