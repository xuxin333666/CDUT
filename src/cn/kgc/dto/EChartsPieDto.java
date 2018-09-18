package cn.kgc.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.kgc.model.EChartsSeries;

public class EChartsPieDto {
	private Map<String, Object> title = new HashMap<>();
	private Map<String, Object> tooltip = new HashMap<>();
	private List<EChartsSeries> series = new ArrayList<>();
	
	

	public EChartsPieDto(String name,List<Map<String, String>> data) {
		title.put("text", name);
		EChartsSeries pieSeries = new EChartsSeries();
		pieSeries.setType("pie");
		pieSeries.setRadius("55%");
		pieSeries.setRoseType("angle");
		pieSeries.setData(data);
//		pieSeries.setName(name);
		series.add(pieSeries);
	}

	public List<EChartsSeries> getSeries() {
		return series;
	}

	public void setSeries(List<EChartsSeries> series) {
		this.series = series;
	}

	public Map<String, Object> getTooltip() {
		return tooltip;
	}

	public void setTooltip(Map<String, Object> tooltip) {
		this.tooltip = tooltip;
	}

	public Map<String, Object> getTitle() {
		return title;
	}

	public void setTitle(Map<String, Object> title) {
		this.title = title;
	}
	
	/*
	 {
		 "series":[{"data":[{"name":"广告","value":"200"}
		 				,{"name":"联盟","value":"300"},
		 				{"name":"部落","value":"400"}],
		 "roseType":"angle",
		 "name":"123",
		 "itemStyle":{
		 			"emphasis":{"
		 						shadowOffsetX":0,
		 						"shadowOffsetY":0,
		 						"shadowBlur":200,
		 						"shadowColor":"rgba(0, 0, 0, 0.5)"}},
		"radius":"55%",
		"type":"pie"}]
	}
 
	 */
	
}
