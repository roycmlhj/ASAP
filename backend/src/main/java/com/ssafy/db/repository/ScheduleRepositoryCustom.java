package com.ssafy.db.repository;

import org.springframework.stereotype.Repository;

import com.ssafy.db.entity.Schedule;

@Repository
public interface ScheduleRepositoryCustom {
	Schedule findByStudyno(int studyno);
}
