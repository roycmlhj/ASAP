package com.ssafy.db.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.db.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer>, BoardRepositoryCustom{
	Page<Board> findAll(Pageable pageable);
}
