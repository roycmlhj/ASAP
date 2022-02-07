package com.ssafy.api.service;

import com.ssafy.api.request.FileSavePostReq;
import com.ssafy.db.entity.UserHomework;

public interface UserHomeworkService {
	UserHomework saveFile(FileSavePostReq fileInfo, int userno, int homeworkno);
	UserHomework getFile(int userhomeworkno);
}
