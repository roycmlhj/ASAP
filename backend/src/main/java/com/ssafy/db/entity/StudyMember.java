package com.ssafy.db.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity(name = "study_member")
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@IdClass(StudyMemberId.class)
public class StudyMember implements Serializable{
//	@ManyToOne(targetEntity = )
//	@JoinColumn(name = "userno")
	@Id
	Integer userno;
//	@ManyToOne(targetEntity = Study.class)
//	@JoinColumn(name = "studyno")
	@Id
	Integer studyno;
	@Column
	Integer position;
	@Column(name = "study_time")
	String studyTime;
	@Column(name = "is_join")
	Integer isJoin;

//	@ManyToOne
//	@JoinColumn(name = "study_rewfwefqfefq")
//	Study study;
}
