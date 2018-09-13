package cn.kgc.model;

import java.util.Date;

public class Group {
	private String id;
	private String name;
	private Date date;
	private String groupManager;
	private String maleCount;
	private String femaleCount;
	private String status;
	private Professional professional;
	public Group() {
	}
	public Group(String id, String name, Date date, String groupManager, String maleCount, String femaleCount,
			String status,Professional professional) {
		this.id = id;
		this.name = name;
		this.date = date;
		this.groupManager = groupManager;
		this.maleCount = maleCount;
		this.femaleCount = femaleCount;
		this.status = status;
		this.professional = professional;
	}
	public Group(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Group [id=" + id + ", name=" + name + ", date=" + date + ", groupManager=" + groupManager
				+ ", maleCount=" + maleCount + ", femaleCount=" + femaleCount + ", professional=" + professional + "]";
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getGroupManager() {
		return groupManager;
	}
	public void setGroupManager(String groupManager) {
		this.groupManager = groupManager;
	}
	public String getMaleCount() {
		return maleCount;
	}
	public void setMaleCount(String maleCount) {
		this.maleCount = maleCount;
	}
	public String getFemaleCount() {
		return femaleCount;
	}
	public void setFemaleCount(String femaleCount) {
		this.femaleCount = femaleCount;
	}
	public Professional getProfessional() {
		return professional;
	}
	public void setProfessional(Professional professional) {
		this.professional = professional;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
