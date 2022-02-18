package com.ssafy.api.service;


import com.ssafy.api.request.FileSavePostReq;
import com.ssafy.db.entity.UserHomework;

public interface UserHomeworkService {
	UserHomework saveFile(FileSavePostReq file, int userno, int homeworkno);
	UserHomework getFile(int userhomeworkno);
	boolean deleteFile(int userhomeworkno);
}
