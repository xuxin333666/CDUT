package cn.kgc.model;

public class Menu {
	private String id;
	private String name;
	private String iconName;
	private String dataRefreshTableName;
	private String dataSearchName;
	private String tagName;
	private String dataUrl;
	private Menu parentMenu;
	
	
	public Menu() {
	}
	public Menu(String id) {
		this.id = id;
	}
	public Menu(String id, String name, String iconName, String dataRefreshTableName, String dataSearchName,
			String dataUrl,String tagName, Menu parentMenu) {
		this.id = id;
		this.name = name;
		this.iconName = iconName;
		this.dataRefreshTableName = dataRefreshTableName;
		this.dataSearchName = dataSearchName;
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
	public String getDataRefreshTableName() {
		return dataRefreshTableName;
	}
	public void setDataRefreshTableName(String dataRefreshTableName) {
		this.dataRefreshTableName = dataRefreshTableName;
	}
	public String getDataSearchName() {
		return dataSearchName;
	}
	public void setDataSearchName(String dataSearchName) {
		this.dataSearchName = dataSearchName;
	}
	public String getDataUrl() {
		return dataUrl;
	}
	public void setDataUrl(String dataUrl) {
		this.dataUrl = dataUrl;
	}
	public Menu getParentMenu() {
		return parentMenu;
	}
	public void setParentMenu(Menu parentMenu) {
		this.parentMenu = parentMenu;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	@Override
	public String toString() {
		return "Menu [id=" + id + ", name=" + name + ", iconName=" + iconName + ", dataRefreshTableName="
				+ dataRefreshTableName + ", dataSearchName=" + dataSearchName + ", dataUrl=" + dataUrl + ", parentMenu="
				+ parentMenu + "]";
	}

}
