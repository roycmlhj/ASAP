package com.ssafy.db.repository;

import java.util.Optional;

import com.ssafy.db.entity.FileData;

public interface FileDataRepositoryCustom {
	long deletebyBoardno(int boardno);
	Optional<FileData> findbyBoardno(int boardno);
}
