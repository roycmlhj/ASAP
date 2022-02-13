package com.ssafy.api.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.api.request.UserLoginPostReq;
import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.api.response.HomeworkNStudy;
import com.ssafy.api.response.StudyAnalyze;
import com.ssafy.api.response.StudyTime;
import com.ssafy.api.response.UserDetailInfoRes;
import com.ssafy.api.response.UserLoginPostRes;
import com.ssafy.api.service.AwsS3Service;
import com.ssafy.api.service.HomeworkService;
import com.ssafy.api.service.StudyService;
import com.ssafy.api.service.UserService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.common.util.JwtTokenUtil;
import com.ssafy.common.util.MD5Generator;
import com.ssafy.db.entity.Homework;
import com.ssafy.db.entity.Study;
import com.ssafy.db.entity.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

/**
 * 유저 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "유저 API", tags = {"User"})
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	StudyService studyService;
	@Autowired
	HomeworkService homeworkService;
	@Autowired
	AwsS3Service awsS3Service;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@PostMapping("/login")
	@ApiOperation(value = "로그인", notes = "<strong>이메일과 패스워드</strong>를 통해 로그인 한다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
        @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
        @ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
        @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
	public ResponseEntity<UserLoginPostRes> login(@RequestBody @ApiParam(value="로그인 정보", required = true) UserLoginPostReq loginInfo) {
		String email = loginInfo.getEmail();
		String password = loginInfo.getPassword();
		
		User user = userService.getUserByEmail(email);
		// 탈퇴한 회원인지 확인 + 로그인 요청한 유저로부터 입력된 패스워드 와 디비에 저장된 유저의 암호화된 패스워드가 같은지 확인.(유효한 패스워드인지 여부 확인)
		if(user.getDelFlag() != 1 && passwordEncoder.matches(password, user.getPassword())) {
			// 유효한 패스워드가 맞는 경우, 로그인 성공으로 응답.(액세스 토큰을 포함하여 응답값 전달)
			return ResponseEntity.ok(UserLoginPostRes.of(200, "Success", JwtTokenUtil.getToken(user)));
		}
		// 탈퇴한 회원이거나 유효하지 않는 패스워드인 경우, 로그인 실패로 응답.
		return ResponseEntity.status(401).body(UserLoginPostRes.of(401, "Invalid Password", null));
	}
	
	@PostMapping("/signup")
	@ApiOperation(value = "회원 가입", notes = "<strong>이메일과 패스워드</strong>를 통해 회원가입 한다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<? extends BaseResponseBody> signUp(
			@RequestBody @ApiParam(value="회원가입 정보", required = true) UserRegisterPostReq registerInfo) {
		User user = userService.signUp(registerInfo);
		
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
	
	@GetMapping("/{email}")
	@ApiOperation(value = "회원 Email 조회", notes = "회원 Email이 존재하는지 여부를 응답한다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 있음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<? extends BaseResponseBody> userCheck (
			@PathVariable("email") @ApiParam(value = "확인할 회원의 이메일", required = true) String email) throws NoSuchElementException{
		try {
			User user = userService.getUserByEmail(email);
		} catch (Exception e) {
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));	
		}
		return ResponseEntity.status(404).body(BaseResponseBody.of(404, "이미 존재하는 이메일입니다."));
	}
	
	@GetMapping("/nickname/{nickname}")
	@ApiOperation(value = "회원 닉네임 조회", notes = "회원 닉네임이 존재하는지 여부를 응답한다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 있음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<? extends BaseResponseBody> nicknameCheck (
			@PathVariable("nickname") @ApiParam(value = "확인할 회원의 닉네임", required = true) String nickname) throws NoSuchElementException{
		try {
			User user = userService.getUserByNickname(nickname);
		} catch (Exception e) {
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));	
		}
		return ResponseEntity.status(404).body(BaseResponseBody.of(404, "이미 존재하는 이메일입니다."));
	}

	
	@PostMapping("/{userno}")
	@ApiOperation(value = "회원정보 수정", notes = "회원의 정보를 수정한다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<UserLoginPostRes> modifyUser(
			@RequestBody @ApiParam(value = "회원 수정정보", required = false) UserRegisterPostReq modifyInfo,
			@PathVariable("userno") @ApiParam(value = "수정할 회원의 userno", required = true) int userno){ 
		User user = userService.modifyUser(userno, modifyInfo);
		return ResponseEntity.ok(UserLoginPostRes.of(200, "Success", JwtTokenUtil.getToken(user)));
	}
	
	@DeleteMapping("/{userno}")
	@ApiOperation(value = "회원탈퇴", notes = "회원 탈퇴한다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<? extends BaseResponseBody> deleteUser(
			@PathVariable("userno") @ApiParam(value = "탈퇴할 회원의 userno", required = true) int userno){
		userService.deleteUser(userno);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
	
	@GetMapping("detail/{userno}")
	@ApiOperation(value = "내 정보 상세보기", notes = "내 상세 정보를 본다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<UserDetailInfoRes> detailUser(@PathVariable("userno") @ApiParam(value = "조회할 회원의 userno", required = true) int userno){
		User user = userService.getUserByUserno(userno);
		List<Study> studyList = studyService.getStudyList(userno);
		List<HomeworkNStudy> onHomeworkList = homeworkService.getUserHomeworkList(userno, 0);
		List<HomeworkNStudy> doneHomeworkList = homeworkService.getUserHomeworkList(userno, 1);
		
		StudyAnalyze studyAnalyze = new StudyAnalyze();
		int total_time = 0;
		for(int i = 0; i < studyList.size(); i++) {
			int studyTime = studyService.getStudyTime(userno, studyList.get(i).getStudyno());
			total_time += studyTime;
			StudyTime studyTimeAdd = new StudyTime();
			studyTimeAdd.setStudyName(studyList.get(i).getStudyname());
			studyTimeAdd.setStudyno(studyList.get(i).getStudyno());
			studyTimeAdd.setTime(studyTime);
			studyAnalyze.addStudyTime(studyTimeAdd);
		}
		studyAnalyze.setTotal_time(total_time);

		return ResponseEntity.status(200).body(UserDetailInfoRes.of(user, studyList, onHomeworkList, doneHomeworkList, studyAnalyze));
	}
	
	@PostMapping("upload/{userno}")
	@ApiOperation(value = "프로필사진 업로드", notes = "사진을 업로드한다") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "확장자 오류"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<UserLoginPostRes> uploadProfile(
			@PathVariable("userno") @ApiParam(value = "업로드할 회원의 userno", required = true) int userno,
			@RequestPart("image") MultipartFile files){
		try {
			if(!files.isEmpty()) {
				String contentType = files.getContentType();
				if(!(contentType.contains("image/jpeg") || 
						contentType.contains("image/png") || 
							contentType.contains("image/gif"))){
					return ResponseEntity.ok(UserLoginPostRes.of(404, "사용할 수 없는 확장자입니다."));
                }
				String ogname = files.getOriginalFilename();
				String extention = FilenameUtils.getExtension(ogname);
				String newfilename = new MD5Generator(FilenameUtils.getBaseName(ogname)).toString();
				String path = awsS3Service.upload(files, "image", newfilename+"."+extention);
				userService.saveProfile(path, userno);
			}else {
				userService.saveProfile("no", userno);
			}
        } catch(Exception e) {
            e.printStackTrace();
        }
		
		User user = userService.getUserByUserno(userno);
		
		return ResponseEntity.ok(UserLoginPostRes.of(200, "Success", JwtTokenUtil.getToken(user)));
	}
}
