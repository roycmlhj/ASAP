package com.ssafy.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.api.request.AddTimeReq;
import com.ssafy.api.request.ScheduleCreatePostReq;
import com.ssafy.api.request.SchedulePutReq;
import com.ssafy.api.request.StudyAcceptPutReq;
import com.ssafy.api.request.StudyApplyPostReq;
import com.ssafy.api.request.StudyCreatePostReq;
import com.ssafy.api.request.StudyPutReq;
import com.ssafy.api.response.StudyInfo;
import com.ssafy.db.entity.Homework;
import com.ssafy.db.entity.Schedule;
import com.ssafy.db.entity.Study;
import com.ssafy.db.entity.StudyMember;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.HomeworkRepository;
import com.ssafy.db.repository.ScheduleRepository;
import com.ssafy.db.repository.StudyMemberRepository;
import com.ssafy.db.repository.StudyRepository;
import com.ssafy.db.repository.UserRepository;

@Service("studyService")
public class StudyServiceImpl implements StudyService {

	@Autowired
	StudyRepository studyRepository;
	@Autowired
	HomeworkRepository homeworkRepository;
	@Autowired
	ScheduleRepository scheduleRepository;
	@Autowired
	StudyMemberRepository studyMemberRepository;
	@Autowired
	UserRepository userRepository;
	
	@Override
	public boolean createStudy(StudyCreatePostReq studyInfo) {
		Study study = new Study();
		study.setStudyname(studyInfo.getStudyname());
		study.setCategory(studyInfo.getCategory());
		study.setDescription(studyInfo.getDescription());
		study.setMemberno(studyInfo.getMemberno());
		study.setMaker(studyInfo.getMaker());
		String interests = "";
		for(int i = 0; i < studyInfo.getInterests().size(); i++) {
			interests += "#";
			interests += studyInfo.getInterests().get(i);
		}
		study.setInterests(interests);
		
		
		if(studyRepository.save(study) != null) {
			if(Optional.ofNullable(userRepository.findById(studyInfo.getUserno())).get() != null) {
				//나중에 여기에 study member 테이블에 이미 있는 값을 넣는지도 확인해보자.
				StudyMember studyMember = new StudyMember();
				studyMember.setStudyno(studyRepository.findByStudyname(studyInfo.getStudyname()).get().getStudyno());
				studyMember.setUserno(studyInfo.getUserno());
				studyMember.setPosition(0);
				studyMemberRepository.save(studyMember);
				return true;
			}
			else
				return false;
		}
		else
			return false;
	}

	@Override
	public List<Study> getStudyList(int userno) {
		List<Study> studyList = studyRepository.findByUserno(userno).get();
		
		return studyList;
	}

	@Override
	public StudyInfo getStudyInfo(int studyno, String studyName, String category) {
		List<Homework> homeworkList = homeworkRepository.findByStudyno(studyno).get(); //
		Schedule schedule = scheduleRepository.findByStudynoOne(studyno);
		List<StudyMember> members = studyMemberRepository.findByStudyno(studyno);
		List<String> memberImage = new ArrayList<String>();
		for(int i = 0; i < members.size(); i++) {
			if(members.get(i).getPosition() < 2) { 
				memberImage.add(userRepository.findById(members.get(i).getUserno()).get().getImage()); 
				}		
			}
		
		StudyInfo studyInfo = new StudyInfo();
		studyInfo.setHomeworkList(homeworkList);
		studyInfo.setMemberImage(memberImage);
		studyInfo.setStudyName(studyName);
		studyInfo.setCategory(category);
		studyInfo.setStudyno(studyno);
		
		if(schedule != null)
			studyInfo.setNextDate(schedule.getNextDate());
		
		return studyInfo;
	}

	@Override
	public Study getStudyInfo(int studyno) {
		Study study = studyRepository.findById(studyno).get();
		return study;
	}

	@Override
	public boolean applyStudy(StudyApplyPostReq applyInfo) {
		if(Optional.ofNullable(studyRepository.findById(applyInfo.getStudyno())).get() != null) {
			if(Optional.ofNullable(userRepository.findById(applyInfo.getUserno())).get() != null) {
			//if() //나중에 여기에 study member 테이블에 이미 있는 값을 넣는지도 확인해보자.
				StudyMember studyMember = new StudyMember();
				studyMember.setStudyno(applyInfo.getStudyno());
				studyMember.setUserno(applyInfo.getUserno());
				studyMemberRepository.save(studyMember);
				return true;
			}
			else
				return false;
		}
		return false;
	}

	@Override
	public boolean acceptStudy(StudyAcceptPutReq acceptInfo) {
		if(Optional.ofNullable(studyRepository.findById(acceptInfo.getStudyno())).get() != null) {
			if(Optional.ofNullable(userRepository.findById(acceptInfo.getUserno())).get() != null) {
			//if() //나중에 여기에 userno, studyno가 study_member에 있는 얘인지 확인해보자.
				if(acceptInfo.getFlag() == 1)
					studyMemberRepository.acceptStudy(acceptInfo);
				else if(acceptInfo.getFlag() == 0)
					studyMemberRepository.rejectStudy(acceptInfo);
				
				return true;
			}
			else
				return false;
		}
		
		return false;
	}

	@Override
	public List<User> getUserList(int studyno) {
		List<StudyMember> members = studyMemberRepository.findByStudyno(studyno);
		List<User> userList = new ArrayList<User>();
		for(int i = 0; i < members.size(); i++) {
			userList.add(userRepository.findById(members.get(i).getUserno()).get());
		}
		return userList;
	}

	@Override
	public Study getStudyByStudyname(String studyname) {
		Study study = studyRepository.findByStudyname(studyname).get();
		return study;
	}

	@Override
	public boolean createSchedule(ScheduleCreatePostReq scheduleInfo) {
		Schedule schedule = new Schedule();
		schedule.setStudyno(scheduleInfo.getStudyno());
		schedule.setNextDate(scheduleInfo.getNextDate());
		
		if(scheduleRepository.save(schedule) != null)
			return true;
		
		return false;
	}

	@Override
	public boolean changeSchdule(SchedulePutReq scheduleChangeInfo) {
		if(scheduleRepository.findById(scheduleChangeInfo.getScheduleno()) != null) {
			scheduleRepository.updateSchdule(scheduleChangeInfo);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteSchedule(int scheduleno) {
		if(scheduleRepository.findById(scheduleno) != null) {
			scheduleRepository.deleteById(scheduleno);
			return true;
		}
		return false;
	}

	@Override
	public List<Schedule> getScheduleList(int studyno) {
		return scheduleRepository.findByStudyno(studyno);
	}

	@Override
	public boolean modifyStudy(StudyPutReq studyPutInfo) {
		if(studyRepository.findById(studyPutInfo.getStudyno()) != null) {
			String interests = "";
			for(int i = 0; i < studyPutInfo.getInterests().size(); i++) {
				interests += "#";
				interests += studyPutInfo.getInterests().get(i);
			}
			
			studyRepository.updateStudy(studyPutInfo, interests);
			return true;
		}
		return false;
	}

	@Override
	public List<StudyMember> getStudyMemberListSimple(int studyno) {
		return studyMemberRepository.findByStudyno(studyno);
	}
	
	@Override
	public boolean addTime(AddTimeReq addTimeInfo) {
		if(!userRepository.findById(addTimeInfo.getUserno()).isPresent() || !studyRepository.findById(addTimeInfo.getStudyno()).isPresent())
			return false;
		else {
			StudyMember studyMember = studyMemberRepository.findByUsernoNStudyNo(addTimeInfo.getUserno(), addTimeInfo.getStudyno());
			int OrginSec = TimeToSec(studyMember.getStudyTime());
			int	AddSec = TimeToSec(addTimeInfo.getTime());
			
			studyMemberRepository.addTime(addTimeInfo.getUserno(), addTimeInfo.getStudyno(), SecToTime(OrginSec + AddSec));
			return true;
		}
	}
	
	@Override
	public int getStudyTime(int userno, int studyno) {
		String studyTimeStr = studyMemberRepository.findByUsernoNStudyNo(userno, studyno).getStudyTime();
		int studyTime = TimeToSec(studyTimeStr);
		studyTime /= 60;
		return studyTime;
	}
	
	@Override
	public Study getStudyByStudyno(int studyno) {
		return studyRepository.findById(studyno).get();
	}

	
	public int TimeToSec(String time) {
		int sec = 0;
		String[] timeSplit = time.split(":");
		
		sec += Integer.parseInt(timeSplit[0]) * 3600;
		sec += Integer.parseInt(timeSplit[1]) * 60;
		sec += Integer.parseInt(timeSplit[2]);
		return sec;
	}

	public String SecToTime(int sec) {
		String time = "";
		
		time += Integer.toString(sec / 3600);
		sec %= 3600;
		time += ":";
		time += Integer.toString(sec / 60);
		sec %= 60;
		time += ":";
		time += Integer.toString(sec);
		return time;
	}


}
