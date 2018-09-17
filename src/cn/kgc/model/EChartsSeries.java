package cn.kgc.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EChartsSeries {
	private String name;
	private String type;
	private String step;
	private String radius;
	private String roseType;
	private Map<String, Map<String,Object>> itemStyle = new HashMap<>();
	private List<?> data = new ArrayList<>();
	
	public EChartsSeries() {
		Map<String,Object> tempMap = new HashMap<>();
		tempMap.put("shadowBlur", 200);
		tempMap.put("shadowOffsetX", 0);
		tempMap.put("shadowOffsetY", 0);
		tempMap.put("shadowColor", "rgba(0, 0, 0, 0.5)");
		itemStyle.put("emphasis", tempMap);
	}
	
	

	public EChartsSeries(String name, String type) {
		this.name = name;
		this.type = type;
	}



	public void setName(String name) {
		this.name = name;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRadius() {
		return radius;
	}

	public void setRadius(String radius) {
		this.radius = radius;
	}

	public String getRoseType() {
		return roseType;
	}

	public void setRoseType(String roseType) {
		this.roseType = roseType;
	}

	public Map<String, Map<String, Object>> getItemStyle() {
		return itemStyle;
	}

	public void setItemStyle(Map<String, Map<String, Object>> itemStyle) {
		this.itemStyle = itemStyle;
	}

	public String getName() {
		return name;
	}

	public List<?> getData() {
		return data;
	}



	public String getStep() {
		return step;
	}



	public void setStep(String step) {
		this.step = step;
	}
	
		
		
		
}
