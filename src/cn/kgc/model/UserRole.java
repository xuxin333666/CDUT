package cn.kgc.model;

import java.util.Date;

public class UserRole {
	private String id;
	private User user;
	private Role role;
	private Date date;
	@Override
	public String toString() {
		return "UserRole [id=" + id + ", user=" + user + ", role=" + role + ", date=" + date + "]";
	}
	public UserRole(String id, User user, Role role, Date date) {
		super();
		this.id = id;
		this.user = user;
		this.role = role;
		this.date = date;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public UserRole() {
	}
	
}
