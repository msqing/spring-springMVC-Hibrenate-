package com.techsun.tccms.service.impl;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techsun.tccms.dao.UserDao;
import com.techsun.tccms.entity.User;
import com.techsun.tccms.service.AdminService;

@Service
@Transactional
public @Data class AdminServiceImpl implements AdminService {
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public void addUser(User user) {
		System.out.println(user.getName());
		this.userDao.save(user);
		this.userDao.deleteAll();
	}

}
