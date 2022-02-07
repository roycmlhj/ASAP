package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("FileSavePostRequest")
public class FileSavePostReq {
	@ApiModelProperty(name = "스터디 게시글 번호")
	int boardno;
	@ApiModelProperty(name = "파일 경로")
	String filepath;
	@ApiModelProperty(name = "서버 파일 이름")
	String filename;
	@ApiModelProperty(name = "파일 이름")
	String ogfilename;
}
