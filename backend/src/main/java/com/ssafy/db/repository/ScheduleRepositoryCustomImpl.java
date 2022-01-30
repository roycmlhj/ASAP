package com.ssafy.db.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

//import com.mysql.cj.x.protobuf.MysqlxCrud.Limit;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.QSchedule;
import com.ssafy.db.entity.Schedule;

public class ScheduleRepositoryCustomImpl extends QuerydslRepositorySupport implements ScheduleRepositoryCustom {
	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	QSchedule qSchedule = QSchedule.schedule;
	
	public ScheduleRepositoryCustomImpl() {
		super(Schedule.class);
	}

	@Override
	public Schedule findByStudyno(int studyno) {
		Schedule schedule = jpaQueryFactory.selectFrom(qSchedule)
				.where(qSchedule.studyno.eq(studyno))
				.orderBy(qSchedule.nextDate.desc())
				.fetchFirst();
		return schedule;
	}

}
