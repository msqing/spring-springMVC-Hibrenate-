package com.techsun.tccms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techsun.tccms.entity.User;

public interface UserDao extends JpaRepository<User, String> {
	

}
