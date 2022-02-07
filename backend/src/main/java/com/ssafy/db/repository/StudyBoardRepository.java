package com.ssafy.db.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.api.request.StudyBoardPutReq;
import com.ssafy.db.entity.StudyBoard;

@Repository
public interface StudyBoardRepository extends JpaRepository<StudyBoard, Integer>, StudyBoardRepositoryCustom{

	Optional<List<StudyBoard>> findByStudyno(int studyno);
	@Transactional
	void deleteByStudyno(int studyno);

}
