package com.ssafy.api.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.api.response.BoardMember;
import com.ssafy.db.entity.FileData;
import com.ssafy.db.entity.Homework;
import com.ssafy.db.entity.StudyBoard;
import com.ssafy.db.entity.StudyMember;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.BoardRepository;
import com.ssafy.db.repository.HomeworkRepository;
import com.ssafy.db.repository.ScheduleRepository;
import com.ssafy.db.repository.StudyBoardRepository;
import com.ssafy.db.repository.StudyMemberRepository;
import com.ssafy.db.repository.StudyRepository;
import com.ssafy.db.repository.UserHomeworkRepository;
import com.ssafy.db.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	HomeworkRepository homeworkRepository;
	@Autowired
	StudyMemberRepository studyMemberRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	BoardService boardService;
	@Autowired
	UserHomeworkRepository userHomeworkRepository;
	@Autowired
	BoardRepository boardRepository;
	@Autowired
	ScheduleRepository scheduleRepository;
	@Autowired
	StudyBoardRepository studyBoardRepository;
	@Autowired
	StudyRepository studyRepository;
	@Autowired
	FileService fileService;
	@Autowired
	AwsS3Service awsS3Service;
	
private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User signUp(UserRegisterPostReq registerInfo) {
		User user = new User();
		user.setEmail(registerInfo.getEmail());
		user.setPassword(passwordEncoder.encode(registerInfo.getPassword()));
		user.setNickname(registerInfo.getNickname());
		user.setMainCategory(registerInfo.getMainCategory());
		StringBuilder sb = new StringBuilder();
		for (String str : registerInfo.getInterests()) {
			sb.append("#"+str);
		}
		user.setInterests(sb.toString());
		return userRepository.save(user);
	}

	@Override
	public User getUserByEmail(String email) {
		User user = userRepository.findByEmail(email).get();
		return user;
	}

	@Override
	public User getUserByUserno(int userno) {
		User user = userRepository.findById(userno).get();
		return user;
	}

	@Override
	public User modifyUser(int userno, UserRegisterPostReq userModifyInfo) {
		User user = getUserByUserno(userno);
		if(!userModifyInfo.getPassword().equals("")) 
			user.setPassword(passwordEncoder.encode(userModifyInfo.getPassword()));
		if(!userModifyInfo.getNickname().equals("")) 
			user.setNickname(userModifyInfo.getNickname());
		if(!userModifyInfo.getMainCategory().equals("")) 
			user.setMainCategory(userModifyInfo.getMainCategory());
		if(!userModifyInfo.getInterests().isEmpty()) {
			StringBuilder sb = new StringBuilder();
			for (String str : userModifyInfo.getInterests()) {
				sb.append("#"+str);
			}
			user.setInterests(sb.toString());
		}
		return userRepository.save(user);
	}

	@Override
	@Transactional
	public boolean deleteUser(int userno) {
		User user = userRepository.findById(userno).get();
		user.setDelFlag(1);
		userRepository.save(user);
		List<Integer> studylist = studyMemberRepository.findStudynobyuserno(userno);
		for (Integer i : studylist) {
  log.info("check1 studyno : {}" , i);
			kickUser(userno, i);			
		}
		return true;
	}
	
	@Override
	public List<BoardMember> getBoardMember(int studyno) {
		List<BoardMember> list = userRepository.findBoardMemberByStudyno(studyno).get();
		return list;
	}

	@Override
	public String getNicknameByUserno(int userno) {
		String nickname = userRepository.findNicknameByUserno(userno);
		return nickname;
	}

	@Override
	public List<User> getUserList() {
		List<User> userlist = userRepository.findAll();
		return userlist;
	}
	
	@Override
	public boolean kickUser(int userno, int studyno) {
    log.info("check2 studyno : {}" + studyno);
		StudyMember studyMember = studyMemberRepository.findByUsernoNStudyNo(userno, studyno);
		
		if(studyMember.getPosition() == 0) {
			StudyMember studyMemberMandate = studyMemberRepository.findByStudynoMandate(studyno).orElse(null);
			if(studyMemberMandate == null) { // 위임할 사람이 없다면 스터디 삭제
				//스터디 신청 인원 studymember에서 삭제
				List<StudyMember> studyMemberList = studyMemberRepository.findByStudyno(studyno);
				for(int i = 0; i < studyMemberList.size(); i++)
					studyMemberRepository.kickStudyMember(studyMemberList.get(i).getUserno(), studyMemberList.get(i).getStudyno());
				
				//board 삭제
				boardRepository.deleteByStudyno(studyno);
				//schedule 삭제
				scheduleRepository.deleteByStudyno(studyno);
				//user_homework 삭제
				List<Homework> homeworkList = homeworkRepository.findByStudyno(studyno).get();
				for(int i = 0; i < homeworkList.size(); i++)
					userHomeworkRepository.deleteByHomeworkno(homeworkList.get(i).getHomeworkno());
				//homework 삭제
				homeworkRepository.deleteByStudyno(studyno);
				
				List<StudyBoard> studyboardList = studyBoardRepository.findStudyBoardByStudyno(studyno).get();
				for (StudyBoard studyBoard : studyboardList) {					
					//file 삭제
					FileData file = fileService.getFilebyBoardno(studyBoard.getBoardno());
					if(file != null) {
						awsS3Service.deleteFile(file.getFilepath());
						fileService.deleteFileByBoardno(studyBoard.getBoardno());
					}
				}
				//study_board 삭제
				studyBoardRepository.deleteByStudyno(studyno);
				//conference 삭제
				
			}
			else {
				//System.out.println("스터디장 위임 시키자");
				studyMemberRepository.studyLeaderMandate(studyMemberMandate.getUserno(), studyno);
			}
		}
		
		//스터디 homework 삭제
		List<Homework> homeworkList = homeworkRepository.findByStudyno(studyno).get();
		for(int i = 0; i < homeworkList.size(); i++) {
			userHomeworkRepository.deleteByIdNUserno(homeworkList.get(i).getHomeworkno(), userno);
		}

		studyMemberRepository.kickStudyMember(userno, studyno);
		
		if(studyMember.getPosition() == 0) {
			//study 삭제
			studyRepository.deleteById(studyno);
		}
		return true;
	}

	@Override
	public String getUserNickname(Integer userno) {
		return userRepository.findById(userno).get().getNickname();
	}

	@Override
	public User saveProfile(String image, int userno) {
		User user = userRepository.findById(userno).get();
		if(image.equals("no")) {
			user.setImage("");
		}else {
			user.setImage(image);
		}
		return userRepository.save(user);
	}
	
	@Override
	public User getUserByNickname(String nickname) {
		User user = userRepository.findByNickname(nickname).get();
		return user;
	}

	@Override
	public boolean countUpHomework(int userno) {
		User user = userRepository.findById(userno).get();
		user.setHomeworkCnt(user.getHomeworkCnt()+1);
		userRepository.save(user);
		return true;
	}
}
