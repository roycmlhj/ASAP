package com.ssafy.common.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.api.request.FileSavePostReq;

public class FileUtil {
	FileSavePostReq filedata;
	
	public FileUtil(MultipartFile files,  String path) {
		try {
			String origFilename = files.getOriginalFilename();
			String filename = new MD5Generator(origFilename).toString();
			/* 실행되는 위치의 'files' 폴더에 파일이 저장됩니다. */
			String savePath = System.getProperty("user.dir") + "\\file_"+path;
			/* 파일이 저장되는 폴더가 없으면 폴더를 생성합니다. */
			if (!new File(savePath).exists()) {
				try{
					new File(savePath).mkdir();
				}
				catch(Exception e){
					e.getStackTrace();
				}
			}
			String filePath = savePath + "\\" + filename;
			files.transferTo(new File(filePath));
			
			FileSavePostReq file = new FileSavePostReq();
			file.setFilepath(filePath);
			file.setOgfilename(origFilename);
			file.setFilename(filename);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public FileSavePostReq toFile(){
		return filedata;
	}
}
