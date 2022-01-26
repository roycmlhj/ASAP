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
	@ApiModelProperty(name="User entity")
	User user;
	
	public UserRes of(User user) {
		UserRes res = new UserRes();
		res.user = user;
		
		return res;
	}
}
