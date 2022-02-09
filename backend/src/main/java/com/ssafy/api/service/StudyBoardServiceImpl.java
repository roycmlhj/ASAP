package com.ssafy.api.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.api.request.FileSavePostReq;
import com.ssafy.api.request.StudyBoardCreatePostReq;
import com.ssafy.api.request.StudyBoardPutReq;
import com.ssafy.common.util.FileUtil;
import com.ssafy.db.entity.StudyBoard;
import com.ssafy.db.repository.StudyBoardRepository;

@Service("studyBoardService")
public class StudyBoardServiceImpl implements StudyBoardService {
	@Autowired
	StudyBoardRepository studyBoardRepository;
	@Autowired
	FileService fileService;
	
	@Override
	@Transactional
	public boolean createStudyBoard(StudyBoardCreatePostReq studyBoardInfo, MultipartFile files) {
		StudyBoard studyBoard = new StudyBoard();
		studyBoard.setContent(studyBoardInfo.getContent());
		studyBoard.setStudyno(studyBoardInfo.getStudyno());
		studyBoard.setTitle(studyBoardInfo.getTitle());
		studyBoard.setUserno(studyBoardInfo.getUserno());
		int boardno = -1;
		boardno = studyBoardRepository.save(studyBoard).getBoardno();
		if(boardno >= 0) {
			if(!ObjectUtils.isEmpty(files)) {
				if(boardno >= 0) {
					FileSavePostReq file = new FileUtil(files, "boardfiles").toFile();
					fileService.saveFile(file, boardno);
				}
			}
			return true;
		}else {
			return false;
		}
	}

	@Override
	public List<StudyBoard> getStudyBoardList(int studyno) {
		List<StudyBoard> studyBoardList = studyBoardRepository.findByStudyno(studyno).get();
		
		return studyBoardList;
	}

	@Override
	public StudyBoard getStudyBoardDetail(int boardno) {
		StudyBoard studyBoard = studyBoardRepository.findById(boardno).get();
		
		return studyBoard;
	}

	@Override
	@Transactional
	public boolean modifyStudyBoard(StudyBoardPutReq studyBoardPutInfo, MultipartFile files) {
		if(Optional.ofNullable(studyBoardRepository.findById(studyBoardPutInfo.getBoardno())).get() != null) {
			studyBoardRepository.modifyStudyBoard(studyBoardPutInfo);
			fileService.deleteFileByBoardno(studyBoardPutInfo.getBoardno());
			if(!ObjectUtils.isEmpty(files)) {
				FileSavePostReq file = new FileUtil(files, "boardfiles").toFile();
				fileService.saveFile(file, studyBoardPutInfo.getBoardno());
			}
			return true;
		}
		else
			return false;
	}

	@Override
	@Transactional
	public boolean deleteStudyBoard(int boardno) {
		if(Optional.ofNullable(studyBoardRepository.findById(boardno)).get() != null) {
			studyBoardRepository.deleteStudyBoard(boardno);
			fileService.deleteFileByBoardno(boardno);
			return true;
		}
		else {
			return false;
		}
	}
}
