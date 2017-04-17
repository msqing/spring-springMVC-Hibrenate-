package com.techsun.tccms.service;

public interface LogService {

	/**
	 * 记录访问日志
	 * @param browser
	 * @param os
	 * @param remoteAddr
	 * @param servletPath
	 * @param more
	 */
	void log(String user, String browser, String os, String remoteAddr, String servletPath, String more);
}
