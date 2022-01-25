package com.ssafy.db.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	int userno;
	@Column
	String email;
	@JsonIgnore
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	String password;
	@Column
	String nickname;
	@Column(name = "main_category")
	String mainCategory;
	@Column
	String interests;
	/////////////////////////
	@Column
	String regidate;
	@Column(name = "is_admin")
	int isAdmin;
	@Column
	int exp;
	@Column
	int level;
	@Column(name = "del_flag")
	int delFlag;
	
	@OneToMany(mappedBy = "study_member") //클래스 이름 or 테이블 이름
	List<StudyMember> studyMemberList = new ArrayList<>();
}
