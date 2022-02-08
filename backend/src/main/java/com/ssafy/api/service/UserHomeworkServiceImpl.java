package com.ssafy.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.api.request.FileSavePostReq;
import com.ssafy.db.entity.UserHomework;
import com.ssafy.db.repository.UserHomeworkRepository;

@Service("userHomeworkService")
public class UserHomeworkServiceImpl implements UserHomeworkService {
	@Autowired
	UserHomeworkRepository userHomeworkRepository;

	@Override
	public UserHomework saveFile(FileSavePostReq fileInfo, int userno, int homeworkno) {
		UserHomework userHomework = userHomeworkRepository.findByUsernoAndHomeworkno(userno, homeworkno).get();
		userHomework.setFilename(fileInfo.getFilename());
		userHomework.setFilepath(fileInfo.getFilepath());
		userHomework.setOgfilename(fileInfo.getOgfilename());
		userHomework.setIsDone(1);
		return userHomeworkRepository.save(userHomework);
	}

	@Override
	public UserHomework getFile(int userhomeworkno) {
		return null;
	}
	
}
