package com.ssafy.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.api.request.FileSavePostReq;
import com.ssafy.db.entity.FileData;
import com.ssafy.db.repository.FileRepository;

@Service("fileService")
public class FileServiceImpl implements FileService {
	@Autowired
	FileRepository fileRepository;

	@Override
	public FileData saveFile(FileSavePostReq fileInfo, int boardno) {
		FileData file = new FileData();
		file.setBoardno(boardno);
		file.setFilepath(fileInfo.getFilepath());
		file.setFilename(fileInfo.getFilename());
		file.setOgfilename(fileInfo.getOgfilename());
		return fileRepository.save(file);
		
	}

	@Override
	public FileData getFile(int fileno) {
		FileData file = fileRepository.findById(fileno).get();
		return file;
	}

	@Override
	public boolean deleteFileByBoardno(int boardno) {
		if(fileRepository.deletebyBoardno(boardno) > 0) {
			return true;
		}else {
			return false;
		}
	}
}
