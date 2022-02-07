package com.ssafy.api.service;

import com.ssafy.api.request.FileSavePostReq;
import com.ssafy.db.entity.File;

public interface FileService {
	File saveFile(FileSavePostReq fileInfo);
	File getFile(int fileno);
}
