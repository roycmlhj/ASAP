package com.ssafy.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.api.request.SchedulePutReq;
import com.ssafy.db.entity.Schedule;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer>, ScheduleRepositoryCustom{
	List<Schedule> findByStudyno(int studyno);
}
