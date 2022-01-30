package com.ssafy.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.db.entity.StudyMember;

@Repository
public interface StudyMemberRepository extends JpaRepository<StudyMember, Integer>, StudyMemberRepositoryCustom{
	List<StudyMember> findByStudyno(int studyno);
	void deleteByUserno(int userno);
}