package com.ssafy.db.repository;

import java.util.Optional;

import com.ssafy.db.entity.EmailConfirm;

public interface EmailConfirmRepositoryCustom {
	Optional<EmailConfirm> findbyEmail(String email);
}
