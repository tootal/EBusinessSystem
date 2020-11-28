package com.demo.model;

/**
 * 实体类-管理
 * @author MouseHappy
 */
public class Admin {
	/**
	 * create table admin
	(
		id int primary key,
		username varchar(40) not null unique,
		password varchar(40) not null,
	);
	 */
	private int id;
	private String username;
	private String password;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
