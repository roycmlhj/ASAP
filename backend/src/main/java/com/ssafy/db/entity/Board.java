package com.ssafy.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Getter;
import lombok.Setter;

@DynamicInsert
@DynamicUpdate
@Entity(name = "board")
@Getter
@Setter
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer boardno;
	@Column
	String boardname;
	@Column
	Integer userno;
	@Column
	String timestamp;
	@Column
	Integer studyno;
	@Column 
	String boarddescription;
	@Column
	String link;
	@Column
	String contactlink;
	@Column
	Integer maxmember;
	@Column(name = "is_recruit")
	Integer isRecruit;
	@Column
	Integer hit;
	@Column
	String interests;
	@Column
	String category;
	@Column
	String nickname;
	
}
