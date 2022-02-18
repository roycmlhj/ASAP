package com.ssafy.db.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.EmailConfirm;
import com.ssafy.db.entity.QEmailConfirm;

public class EmailConfirmRepositoryCustomImpl implements EmailConfirmRepositoryCustom {
	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	QEmailConfirm qEmailConfirm = QEmailConfirm.emailConfirm;

	@Override
	public Optional<EmailConfirm> findbyEmail(String email) {
		EmailConfirm emailConfirm = jpaQueryFactory.select(qEmailConfirm).from(qEmailConfirm)
				.where(qEmailConfirm.email.eq(email))
				.fetchFirst();
		return Optional.ofNullable(emailConfirm);
	}
}
