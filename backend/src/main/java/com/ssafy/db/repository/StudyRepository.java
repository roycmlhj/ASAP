package com.ssafy.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.db.entity.Study;

@Repository
public interface StudyRepository extends JpaRepository<Study, Integer>, StudyRepositoryCustom{
	
}
