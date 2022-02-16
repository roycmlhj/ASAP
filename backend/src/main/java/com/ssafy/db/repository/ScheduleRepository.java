package com.ssafy.db.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.db.entity.Schedule;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer>, ScheduleRepositoryCustom{
	List<Schedule> findByStudyno(int studyno);
	@Transactional
	void deleteByStudyno(int studyno);
	Optional<Schedule> findByNextDate(String nextDate);
}
