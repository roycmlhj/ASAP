package com.ssafy.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.api.request.HomeworkCreatePostReq;
import com.ssafy.api.request.HomeworkPutReq;
import com.ssafy.api.response.HomeworkNStudy;
import com.ssafy.db.entity.Homework;
import com.ssafy.db.entity.StudyMember;
import com.ssafy.db.entity.UserHomework;
import com.ssafy.db.repository.HomeworkRepository;
import com.ssafy.db.repository.StudyMemberRepository;
import com.ssafy.db.repository.UserHomeworkRepository;

@Service("homeworkService")
public class HomeworkServiceImpl implements HomeworkService {
	@Autowired
	HomeworkRepository homeworkRepository;
	@Autowired
	StudyMemberRepository studyMemberRepository;
	@Autowired
	UserHomeworkRepository userHomeworkRepository;
	@Autowired
	StudyService studyService;

	@Override
	public boolean createHomework(HomeworkCreatePostReq homeworkInfo) {
		Homework homework = new Homework();
		homework.setStudyno(homeworkInfo.getStudyno());
		homework.setUserno(homeworkInfo.getUserno());
		homework.setTitle(homeworkInfo.getTitle());
		homework.setEndDate(homeworkInfo.getEndDate());
		homework.setContent(homeworkInfo.getContent());
		
		int homeworkno = homeworkRepository.save(homework).getHomeworkno();
		
		List<StudyMember> studyMember = studyMemberRepository.findByStudyno(homeworkInfo.getStudyno());
		for(int i = 0; i < studyMember.size(); i++) {
			UserHomework userHomework = new UserHomework();
			userHomework.setUserno(studyMember.get(i).getUserno());
			userHomework.setHomeworkno(homeworkno);
			userHomeworkRepository.save(userHomework);
		}
		
		return true;
	}

	@Override
	public List<Homework> getHomeworkList(int studyno) {
		List<Homework> homeworkList = homeworkRepository.findByStudyno(studyno).get();
		
		return homeworkList;
	}

	@Override
	public boolean modifyHomework(HomeworkPutReq homeworkPutInfo) {
		if(Optional.ofNullable(homeworkRepository.findById(homeworkPutInfo.getHomeworkno())).get() != null) {
			homeworkRepository.modifyHomework(homeworkPutInfo);
			return true;
		}
		return false;
	}

	@Override
	public boolean deletehomework(int homeworkno) {
		if(Optional.ofNullable(homeworkRepository.findById(homeworkno)).get() != null) {
			userHomeworkRepository.deleteUserHomework(homeworkno);
			homeworkRepository.deleteHomework(homeworkno);
			return true;
		}
		return false;
	}
	
	// flag 0 = 진행중 과제 / 1 = 완료 과제 / 2 = 전체 과제 
	@Override
	@Transactional
	public List<HomeworkNStudy> getUserHomeworkList(int userno, int flag) {
		List<UserHomework> userhomeworkList = userHomeworkRepository.findUserHomeworkByuserno(userno).get();
		List<HomeworkNStudy> homeworkList = new ArrayList<>();
		if(flag == 2) {
			for (UserHomework userHomework : userhomeworkList) {
					Homework homework = homeworkRepository.findById(userHomework.getHomeworkno()).get();
					String studyname = studyService.getStudyByStudyno(homework.getStudyno()).getStudyname();
					homeworkList.add(new HomeworkNStudy(homework, studyname));
			}
		}else {
			for (UserHomework userHomework : userhomeworkList) {
				if(userHomework.getIsDone() == flag) {
					Homework homework = homeworkRepository.findById(userHomework.getHomeworkno()).get();
					String studyname = studyService.getStudyByStudyno(homework.getStudyno()).getStudyname();
					homeworkList.add(new HomeworkNStudy(homework, studyname));
				}
			}
		}
		return homeworkList;
	}
	
	@Override
	public Homework getSHomeworkDetail(int homeworkno) {
		Homework homework = homeworkRepository.findById(homeworkno).get();
		
		return homework;
	}

	@Override
	public List<UserHomework> getUploadHomework(int homeworkno) {
		List<UserHomework> userHomeworkList = userHomeworkRepository.findByHomeworkno(homeworkno);
		return userHomeworkList;
	}

}
