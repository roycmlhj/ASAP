package com.ssafy.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.api.request.FileSavePostReq;
import com.ssafy.db.entity.File;
import com.ssafy.db.repository.FileRepository;

@Service("fileService")
public class FileServiceImpl implements FileService {
	@Autowired
	FileRepository fileRepository;

	@Override
	public File saveFile(FileSavePostReq fileInfo) {
		File file = new File();
		file.setBoardno(fileInfo.getBoardno());
		file.setFilepath(fileInfo.getFilepath());
		file.setFilename(fileInfo.getFilename());
		file.setOgfilename(fileInfo.getOgfilename());
		return fileRepository.save(file);
		
	}

	@Override
	public File getFile(int fileno) {
		File file = fileRepository.findById(fileno).get();
		return file;
	}
}
