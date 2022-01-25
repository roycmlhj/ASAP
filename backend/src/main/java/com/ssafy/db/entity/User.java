package com.ssafy.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user") // default = entity name
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer userno = null;
	
	String email;
	
	@JsonIgnore
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	String password;
	
	String nickname;
	
	@Column(name = "main_category")
	String mainCategory;
	
	String interests;
	
	/////////////////////////
	
	String regidate;
	
	@Column(name = "is_admin")
	Integer isAdmin;
	
	Integer exp;
	
	Integer level;
	
	@Column(name = "del_flag")
	Integer delFlag;
}
