package com.ssafy.api.response;

import java.util.List;

import com.ssafy.db.entity.User;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserListResponse")
public class UserListRes {
	@ApiModelProperty(name="User List entity")
	List<User> userlist;
	
	public static UserListRes of(List<User> userlist) {
		UserListRes res = new UserListRes();
		res.userlist = userlist;
		return res;
	}
}
