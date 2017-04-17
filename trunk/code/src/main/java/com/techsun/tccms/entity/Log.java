package com.techsun.tccms.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "access_log")
public class Log implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String user;
	private String browser;
	private String os;
	private String remoteAddr;
	private String servletPath;
	private String more;
	private Date applicationTime;
	private Date databaseTime;
	private Date insertTime;
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="visit_user")
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	@Column(name="browser")
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	@Column(name="os")
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	@Column(name="remote_addr")
	public String getRemoteAddr() {
		return remoteAddr;
	}
	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}
	@Column(name="servlet_path")
	public String getServletPath() {
		return servletPath;
	}
	public void setServletPath(String servletPath) {
		this.servletPath = servletPath;
	}
	@Column(name="more")
	public String getMore() {
		return more;
	}
	public void setMore(String more) {
		this.more = more;
	}
	@Column(name="application_time")
	public Date getApplicationTime() {
		return applicationTime;
	}
	public void setApplicationTime(Date applicationTime) {
		this.applicationTime = applicationTime;
	}
	@Column(name="database_time", insertable = false, updatable = false)
	public Date getDatabaseTime() {
		return databaseTime;
	}
	public void setDatabaseTime(Date databaseTime) {
		this.databaseTime = databaseTime;
	}
	@Column(name="insert_time")
	public Date getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
}
