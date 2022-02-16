package com.ssafy.db.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

public interface BoardRepositoryCustom {
	Optional<List<Integer>> findBoardnoByUserno(int userno);
}
