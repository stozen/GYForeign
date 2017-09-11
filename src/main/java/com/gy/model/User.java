package com.gy.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/**
 * @author Chencongye
 * @version 0.0.1
 * @introduce 这是用户实体类
 * @date 2017.9.11
 */
@Entity
@Table(name="tb_gyuser")
public class User {
	
	/**
	 * 创建ID字段
	 */
	private int userid;
	
	/**
	 * 创建用户名字段
	 */
	
	private String username;
	
	/**
	 * 创建用户密码字段
	 */
	
	private String password;
	
	/**
	 * 创建用户登录时间
	 */
	private Date logintime;
	
	/**
	 * 创建用户注册时间
	 */
	private Date registtime;
	
	
	/**
	 * 创建默认构造函数
	 */
	public User() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 创建带有参数的构造函数
	 */
	public User(int userid, String username, String password) {
		super();
		this.userid = userid;
		this.username = username;
		this.password = password;
	}

	/**
	 * 创建对应字段的set和get方法
	 */
	@Id
	@GenericGenerator(name="generator",strategy="native")
	@GeneratedValue(generator="generator")
	@Column(name="userid",length=12,nullable=false,unique=true)
	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	@Column(name="username",length=30,nullable=false,insertable=true,updatable=true)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name="passwd",length=40,nullable=false,insertable=true,updatable=true)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 创建toString方法
	 */
	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username
				+ ", password=" + password + "]";
	}
	
}