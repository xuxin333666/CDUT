package cn.kgc.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String realName;
	private String status = "1";
	private Date createDate;
	public User() {
	}
	public User(String username, String password, String realName, String status,Date createDate) {
		this.username = username;
		this.password = password;
		this.realName = realName;
		this.status = status;
		this.createDate = createDate;
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
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", realName=" + realName + ", status=" + status
				+ "]";
	}
	
	
	
}
