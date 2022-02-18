package com.ssafy.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.db.entity.FileData;

@Repository
public interface FileDataRepository extends JpaRepository<FileData, Integer>, FileDataRepositoryCustom{
}
