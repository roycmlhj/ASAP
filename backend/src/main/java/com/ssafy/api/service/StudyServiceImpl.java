package com.ssafy.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.api.request.StudyAcceptPutReq;
import com.ssafy.api.request.StudyApplyPostReq;
import com.ssafy.api.request.StudyCreatePostReq;
import com.ssafy.api.response.StudyInfo;
import com.ssafy.db.entity.Homework;
import com.ssafy.db.entity.Schedule;
import com.ssafy.db.entity.Study;
import com.ssafy.db.entity.StudyMember;
import com.ssafy.db.repository.HomeworkRepository;
import com.ssafy.db.repository.ScheduleRepository;
import com.ssafy.db.repository.StudyMemberRepository;
import com.ssafy.db.repository.StudyRepository;

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
	
	@Override
	public boolean createStudy(StudyCreatePostReq studyInfo) {
		Study study = new Study();
		study.setStudyname(studyInfo.getStudyname());
		study.setCategory(studyInfo.getCategory());
		study.setDescription(studyInfo.getDescription());
		study.setMemberno(studyInfo.getMemberno());
		study.setMaker(studyInfo.getMaker());
		
		if(studyRepository.save(study) != null) {
			//if() //나중에 여기에 userno도 studyno처럼 있는 얘인지 확인해보자.
			//if() //나중에 여기에 study member 테이블에 이미 있는 값을 넣는지도 확인해보자.
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

	@Override
	public List<Study> getStudyList(int userno) {
		List<Study> studyList = studyRepository.findByUserno(userno);
		
		return studyList;
	}

	@Override
	public StudyInfo getStudyInfo(int studyno, String studyName) {
		List<Homework> homeworkList = homeworkRepository.findByStudyno(studyno);
		Schedule schedule = scheduleRepository.findByStudyno(studyno);
		Schedule schedule2 = new Schedule();
		//List<String> memeberImage = ;
		
		return null;
	}

	@Override
	public Study getStudyInfo(int studyno) {
		Study study = studyRepository.findById(studyno).get();
		return study;
	}

	@Override
	public boolean applyStudy(StudyApplyPostReq applyInfo) {
		if(Optional.ofNullable(studyRepository.findById(applyInfo.getStudyno())).get() != null) {
			//if() //나중에 여기에 userno도 studyno처럼 있는 얘인지 확인해보자.
			//if() //나중에 여기에 study member 테이블에 이미 있는 값을 넣는지도 확인해보자.
				StudyMember studyMember = new StudyMember();
				studyMember.setStudyno(applyInfo.getStudyno());
				studyMember.setUserno(applyInfo.getUserno());
				studyMemberRepository.save(studyMember);
				return true;
		}
		return false;
	}

	@Override
	public boolean acceptStudy(StudyAcceptPutReq acceptInfo) {
		if(Optional.ofNullable(studyRepository.findById(acceptInfo.getStudyno())).get() != null) {
			//if() //나중에 여기에 userno도 studyno처럼 있는 얘인지 확인해보자.
			//if() //나중에 여기에 userno, studyno가 study_member에 있는 얘인지 확인해보자.
				if(acceptInfo.getFlag() == 1)
					studyMemberRepository.acceptStudy(acceptInfo);
				else if(acceptInfo.getFlag() == 0)
					studyMemberRepository.rejectStudy(acceptInfo);
				
				return true;
		}
		
		return false;
	}

}
