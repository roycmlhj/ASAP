package com.ssafy.db.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudyMemberId implements Serializable {
	private Integer userno;
	private Integer studyno;
}
