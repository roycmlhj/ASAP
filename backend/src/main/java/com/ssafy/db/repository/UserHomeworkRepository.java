package com.ssafy.db.repository;

import com.ssafy.db.entity.UserHomework;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

/**
 * 유저 모델 관련 디비 쿼리 생성을 위한 JPA Query Method 인터페이스 정의.
 */
@Repository
public interface UserHomeworkRepository extends JpaRepository<UserHomework, Integer>, UserHomeworkRepositoryCustom {
	Optional<UserHomework> findByUsernoAndHomeworkno(int userno, int homeworkno);
	@Transactional
	void deleteByHomeworkno(Integer homeworkno);
	List<UserHomework> findByHomeworkno(int homeworkno);
}