package com.ssafy.api.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.api.request.FileSavePostReq;
import com.ssafy.api.request.StudyBoardCreatePostReq;
import com.ssafy.api.request.StudyBoardPutReq;
import com.ssafy.api.response.StudyBoardListRes;
import com.ssafy.api.response.StudyBoardNNickname;
import com.ssafy.api.response.StudyBoardRes;
import com.ssafy.api.service.AwsS3Service;
import com.ssafy.api.service.FileService;
import com.ssafy.api.service.StudyBoardService;
import com.ssafy.api.service.UserService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.common.util.MD5Generator;
import com.ssafy.db.entity.FileData;
import com.ssafy.db.entity.StudyBoard;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "StudyBoard API", tags = {"StudyBoard"})
@RestController
@RequestMapping("/api/v1/study_board")
public class StudyBoardController {
	@Autowired
	StudyBoardService studyBoardService;
	@Autowired
	FileService fileService;
	@Autowired
	UserService userService;
	@Autowired
	AwsS3Service awsS3Service;
	
	@PostMapping("/create")
	@ApiOperation(value = "????????? ????????? ??????", notes = "????????? ???????????? ????????????.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "??????"),
		@ApiResponse(code = 401, message = "??????"),
		@ApiResponse(code = 500, message = "?????? ??????")
	})
	public ResponseEntity<? extends BaseResponseBody> create(
			@RequestParam(value = "studyno") int studyno,
			@RequestParam(value = "userno") int userno,
			@RequestParam(value = "title") String title,
			@RequestParam(value = "content") String content,
			@RequestPart(required = false) MultipartFile files){
		StudyBoardCreatePostReq studyBoardInfo = new StudyBoardCreatePostReq();
		studyBoardInfo.setStudyno(studyno);
		studyBoardInfo.setUserno(userno);
		studyBoardInfo.setTitle(title);
		studyBoardInfo.setContent(content);
		
		int boardno = studyBoardService.createStudyBoard(studyBoardInfo, files);
		if(boardno >= 0) {
			if(!ObjectUtils.isEmpty(files)) {
				try {
					String ogname = files.getOriginalFilename();
					String extention = FilenameUtils.getExtension(ogname);
					String newfilename = new MD5Generator(FilenameUtils.getBaseName(ogname)).toString(); 
					String filePath = awsS3Service.upload(files, "board", newfilename+"."+extention);
					
					FileSavePostReq file = new FileSavePostReq();
					file.setFilepath(filePath);
					file.setFilename("test");
					file.setOgfilename(ogname);
					fileService.saveFile(file, boardno);
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "??????"));
		} else {
			return ResponseEntity.status(401).body(BaseResponseBody.of(401, "??????"));	
		}
	}
	
	@GetMapping("/boardlist/{studyno}")
	@ApiOperation(value = "???????????? ????????? ?????????", notes = "????????? ?????? ????????? ???????????? ???????????????.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "??????"),
		@ApiResponse(code = 401, message = "??????"),
		@ApiResponse(code = 500, message = "?????? ??????")
	})
	public ResponseEntity<StudyBoardListRes> getStudyBoardList(@PathVariable("studyno") @ApiParam(value = "study pk", required = true) int studyno){
		List<StudyBoard> studyBoardList = studyBoardService.getStudyBoardList(studyno);
		List<StudyBoardNNickname> studyBoardNNicknameList = new ArrayList<>();
		
		for(int i = 0; i < studyBoardList.size(); i++) {
			studyBoardNNicknameList.add(new StudyBoardNNickname(studyBoardList.get(i), 
			userService.getUserNickname(studyBoardList.get(i).getUserno())));
		}
		
		return ResponseEntity.status(200).body(StudyBoardListRes.of(studyBoardNNicknameList));
	};

	@GetMapping("/boardlist/detail/{boardno}")
	@ApiOperation(value = "???????????? ????????? ????????????", notes = "????????? ?????? ????????? ??????????????? ???????????????.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "??????"),
		@ApiResponse(code = 401, message = "??????"),
		@ApiResponse(code = 500, message = "?????? ??????")
	})
	public ResponseEntity<StudyBoardRes> getStudyBoardDetail(@PathVariable("boardno") @ApiParam(value = "board pk", required = true) int boardno){
		StudyBoard studyBoard = studyBoardService.getStudyBoardDetail(boardno);
		String nickname = userService.getUserNickname(studyBoard.getUserno());
		return ResponseEntity.status(200).body(StudyBoardRes.of(studyBoard, nickname));
	};
	
	@PutMapping("/modify")
	@ApiOperation(value = "????????? ????????? ??????", notes = "????????? ???????????? ????????????.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "??????"),
		@ApiResponse(code = 401, message = "??????"),
		@ApiResponse(code = 500, message = "?????? ??????")
	})
	public ResponseEntity<? extends BaseResponseBody> modify(
			@RequestParam(value = "boardno") int boardno,
			@RequestParam(value = "title") String title,
			@RequestParam(value = "content") String content,
			@RequestPart(value = "files", required = false) MultipartFile files){
		StudyBoardPutReq studyBoardPutInfo = new StudyBoardPutReq();
		studyBoardPutInfo.setBoardno(boardno);
		studyBoardPutInfo.setTitle(title);
		studyBoardPutInfo.setContent(content);
		
		if(!ObjectUtils.isEmpty(files)) { // ????????? ??????????????? ???
			FileData boardfile = fileService.getFilebyBoardno(boardno);
			if(boardfile != null) { // ???????????? ????????? ????????? ???????????? ??????
				String filename = boardfile.getFilepath();
				fileService.deleteFileByBoardno(boardno);
				try {
					awsS3Service.deleteFile(filename);
				}catch (Exception e) {
					System.out.println("delete file error"+e.getMessage());
				}
			}
			try {
				String ogname = files.getOriginalFilename();
				String extention = FilenameUtils.getExtension(ogname);
				String newfilename = new MD5Generator(FilenameUtils.getBaseName(ogname)).toString(); 
				String filePath = awsS3Service.upload(files, "board", newfilename+"."+extention);
				
				FileSavePostReq file = new FileSavePostReq();
				file.setFilepath(filePath);
				file.setFilename("test");
				file.setOgfilename(files.getOriginalFilename());
				fileService.saveFile(file, boardno);
			}catch (Exception e) {
				System.out.println("upload file error"+e.getMessage());
			}
		}
		
		if(studyBoardService.modifyStudyBoard(studyBoardPutInfo))
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "??????"));
		else
			return ResponseEntity.status(401).body(BaseResponseBody.of(401, "??????"));
	}
	
	@DeleteMapping("/delete/{boardno}")
	@ApiOperation(value = "????????? ????????? ??????", notes = "????????? ???????????? ????????????.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "??????"),
        @ApiResponse(code = 401, message = "??????"),
        @ApiResponse(code = 500, message = "?????? ??????")
    })
	public ResponseEntity<? extends BaseResponseBody> deleteStudyBoard(
			@PathVariable("boardno") @ApiParam(value = "????????? board pk", required = true) int boardno){
		FileData boardfile = fileService.getFilebyBoardno(boardno);
		if(boardfile != null) {
			String filename = boardfile.getFilepath();
			fileService.deleteFileByBoardno(boardno);
			try {
				awsS3Service.deleteFile(filename);
			}catch(Exception e) {
				System.out.println("delete file error"+e.getMessage());
			}
		}
		if(studyBoardService.deleteStudyBoard(boardno)) {
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
		}
		else
			return ResponseEntity.status(401).body(BaseResponseBody.of(401, "??????"));
	}
	
	@GetMapping("/download/{fileno}")
	@ApiOperation(value = "?????? ????????????", notes = "????????? ??????????????????.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "??????"),
		@ApiResponse(code = 401, message = "??????"),
		@ApiResponse(code = 500, message = "?????? ??????")
	})
	public ResponseEntity<byte[]> fileDownload(
			@PathVariable("fileno") @ApiParam(value = "??????????????? ?????? no", required = true) int fileno) throws IOException {
		FileData file = fileService.getFile(fileno);
		return awsS3Service.getObject(file.getFilepath(), file.getOgfilename());
	}
}
