package com.ssafy.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.db.repository.HomeworkRepository;

@Service("homeworkService")
public class HomeworkServiceImpl implements HomeworkService {
	@Autowired
	HomeworkRepository homeworkRepository;
}
