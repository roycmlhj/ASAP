package com.ssafy.api.response;

import com.ssafy.db.entity.User;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserResponse")
public class UserRes{
	@ApiModelProperty(name="User Email")
	String email;
	@ApiModelProperty(name="User NickName")
	String nickname;
	@ApiModelProperty(name="User Main Category")
	String mainCategory;
	@ApiModelProperty(name="User Interests")
	String interests;
	@ApiModelProperty(name="User Register Date")
	String regidate;
	
	public UserRes of(User user) {
		UserRes res = new UserRes();
		res.setEmail(user.getEmail());
		res.setNickname(user.getNickname());
		res.setMainCategory(user.getMainCategory());
		res.setInterests(user.getInterests());
		res.setRegidate(user.getRegidate());
		return res;
	}
	
}
