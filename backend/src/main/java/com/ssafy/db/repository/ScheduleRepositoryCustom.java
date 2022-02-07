package com.ssafy.db.repository;

import org.springframework.stereotype.Repository;

import com.ssafy.api.request.SchedulePutReq;
import com.ssafy.db.entity.Schedule;

@Repository
public interface ScheduleRepositoryCustom {
	Schedule findByStudynoOne(int studyno);
	void updateSchdule(SchedulePutReq scheduleChangeInfo);
}
