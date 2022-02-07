package com.ssafy.api.service;

import com.ssafy.api.request.FileSavePostReq;
import com.ssafy.db.entity.FileData;

public interface FileService {
	FileData saveFile(FileSavePostReq fileInfo, int boardno);
	FileData getFile(int fileno);
	boolean deleteFileByBoardno(int boardno);
}
