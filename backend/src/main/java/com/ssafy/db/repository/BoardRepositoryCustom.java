package com.ssafy.db.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepositoryCustom {
	List<Integer> findBoardnoByUserno(int userno);
}