package com.xiaour.spring.boot.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 用户表实体类
 * 
 * @author curtin 2019-03-03 16:15:48
 *
 */
public class UserInfo {
	private Integer id;

	private String name;

	private int age;

	private Date createDate;

	private String createUser;

	private Date updateDate;

	private String updateUser;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCreateDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd E a HH:mm:ss");

		return sdf.format(createDate);
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getUpdateDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd E a HH:mm:ss");

		return sdf.format(updateDate);
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

}