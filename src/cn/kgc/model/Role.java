package cn.kgc.model;

import java.util.Date;

public class Role {
	private String id;
	private String name;
	private String desc;
	private Date date;
	
	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", desc=" + desc + ", date=" + date + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Role(String id, String name, String desc, Date date) {
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.date = date;
	}
	public Role() {
	}
}
