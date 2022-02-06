package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("BoardModifyPostRequest")
public class BoardModifyPostReq {
	@ApiModelProperty(name = "게시글 이름", example = "알고리즘 뿌수기 스터디 모집합니다")
	String boardname;
	@ApiModelProperty(name = "게시글 내용", example = "알고리즘 뿌수기 스터디를 모집합니다 관심 있으신 분들은 연락주세요")
	String boarddescription;
	@ApiModelProperty(name = "추가 정보", example = "www.notion.com")
	String link;
	@ApiModelProperty(name = "추가 정보2", example = "01011112222")
	String contactlink;
}
