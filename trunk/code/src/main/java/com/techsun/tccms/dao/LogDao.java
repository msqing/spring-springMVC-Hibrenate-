package com.techsun.tccms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techsun.tccms.entity.Log;

public interface LogDao extends JpaRepository<Log, Long> {
	

}
