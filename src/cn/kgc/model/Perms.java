package cn.kgc.model;

import java.util.Date;

public class Perms {
	private String id;
	private String name;
	private String iconName;
	private String type;
	private Date date;
	private String tagName;
	private String dataUrl;
	private Perms parentMenu;
	
	
	@Override
	public String toString() {
		return "Menu [id=" + id + ", name=" + name + ", iconName=" + iconName + ", type=" + type + ", date=" + date
				+ ", tagName=" + tagName + ", dataUrl=" + dataUrl + ", parentMenu=" + parentMenu + "]";
	}
	public Perms() {
	}
	public Perms(String id) {
		this.id = id;
	}
	public Perms(String id, String name, String iconName, String type, Date date,
			String dataUrl,String tagName, Perms parentMenu) {
		this.id = id;
		this.name = name;
		this.iconName = iconName;
		this.type = type;
		this.date = date;
		this.dataUrl = dataUrl;
		this.tagName = tagName;
		this.parentMenu = parentMenu;
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
	public String getIconName() {
		return iconName;
	}
	public void setIconName(String iconName) {
		this.iconName = iconName;
	}

	public String getDataUrl() {
		return dataUrl;
	}
	public void setDataUrl(String dataUrl) {
		this.dataUrl = dataUrl;
	}
	public Perms getParentMenu() {
		return parentMenu;
	}
	public void setParentMenu(Perms parentMenu) {
		this.parentMenu = parentMenu;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

}
