package com.ssafy.api.service;


import javax.transaction.Transactional;

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
	@Transactional
	public UserHomework saveFile(FileSavePostReq fileInfo, int userno, int homeworkno) {
		UserHomework userHomework = userHomeworkRepository.findByUsernoAndHomeworkno(userno, homeworkno).get();
		userHomework.setFilename(fileInfo.getFilename());
		userHomework.setFilepath(fileInfo.getFilepath());
		userHomework.setOgfilename(fileInfo.getOgfilename());
		userHomework.setIsDone(1);
		return userHomeworkRepository.save(userHomework);
	}

	@Override
	@Transactional
	public UserHomework getFile(int userhomeworkno) {
		UserHomework userHomework = userHomeworkRepository.findById(userhomeworkno).get();
		return userHomework;
	}

	@Override
	public boolean deleteFile(int userhomeworkno) {
		UserHomework userhomework = userHomeworkRepository.findById(userhomeworkno).get();
		userhomework.setFilename(null);
		userhomework.setFilepath(null);
		userhomework.setOgfilename(null);
		userhomework.setIsDone(0);
		userHomeworkRepository.save(userhomework);
		return true;
	}
}
