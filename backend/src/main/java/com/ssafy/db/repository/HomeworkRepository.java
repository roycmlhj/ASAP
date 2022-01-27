package com.ssafy.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.db.entity.Homework;

@Repository
public interface HomeworkRepository extends JpaRepository<Homework, Integer>, HomeworkRepositoryCustom{

}
