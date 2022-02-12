package com.ssafy.db.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ssafy.db.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer>, BoardRepositoryCustom{
	Page<Board> findAll(Pageable pageable);
	List<Board> findByBoardnameContaining(String keyword, Pageable pageable);
	List<Board> findByCategoryContaining(String keyword, Pageable pageable);
	List<Board> findByNicknameContaining(String keyword, Pageable pageable);
	@Transactional
	void deleteByStudyno(int studyno);
	
	@Modifying
	@Query("update board b set b.hit = b.hit + 1 where b.boardno = :boardno")
	int updateHit(@Param("boardno") int boardno);
}
