package cn.kgc.dto;

import java.util.List;
import java.util.Map;

public class treeNode {
	private String text;
	private String icon;
	private String selectedIcon;
	private Map<String, String> state;
	private List<treeNode> nodes;
	
	
	public treeNode() {
	}
	public treeNode(String text, String icon, String selectedIcon, Map<String, String> state, List<treeNode> nodes) {
		this.text = text;
		this.icon = icon;
		this.selectedIcon = selectedIcon;
		this.state = state;
		this.nodes = nodes;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getSelectedIcon() {
		return selectedIcon;
	}
	public void setSelectedIcon(String selectedIcon) {
		this.selectedIcon = selectedIcon;
	}
	public Map<String, String> getState() {
		return state;
	}
	public void setState(Map<String, String> state) {
		this.state = state;
	}
	public List<treeNode> getNodes() {
		return nodes;
	}
	public void setNodes(List<treeNode> nodes) {
		this.nodes = nodes;
	}
	@Override
	public String toString() {
		return "treeNode [text=" + text + ", icon=" + icon + ", selectedIcon=" + selectedIcon + ", state=" + state
				+ ", nodes=" + nodes + "]";
	}

	
}
