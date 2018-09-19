package cn.kgc.model;

import java.util.Date;

public class RolePerms {
	private String id;
	private Role role;
	private Perms perms;
	private Date date;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Perms getPerms() {
		return perms;
	}
	public void setPerms(Perms perms) {
		this.perms = perms;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public RolePerms(String id, Role role, Perms perms, Date date) {
		this.id = id;
		this.role = role;
		this.perms = perms;
		this.date = date;
	}
	public RolePerms() {
	}
	@Override
	public String toString() {
		return "RolePerms [id=" + id + ", role=" + role + ", perms=" + perms + ", date=" + date + "]";
	}
	
}
