package com.sage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sage.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByEmail(String enail);
}
