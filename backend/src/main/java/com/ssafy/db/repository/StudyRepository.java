package com.ssafy.db.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.api.request.StudyApplyPostReq;
import com.ssafy.db.entity.Study;

@Repository
public interface StudyRepository extends JpaRepository<Study, Integer>, StudyRepositoryCustom{
	Optional<Study> findByStudyname(String studyname);
}
