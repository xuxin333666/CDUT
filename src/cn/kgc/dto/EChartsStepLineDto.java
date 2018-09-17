package cn.kgc.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.kgc.model.EChartsSeries;

public class EChartsStepLineDto {
	private Map<String, Object> title = new HashMap<>();
	private Map<String, Object> tooltip = new HashMap<>();
	private Map<String, List<String>> legend = new HashMap<>();
	private Map<String, Object> grid = new HashMap<>();
	private Map<String, Object> xAxis = new HashMap<>();
	private Map<String, Object> yAxis = new HashMap<>();
	private List<EChartsSeries> series = new ArrayList<>();
	public EChartsStepLineDto() {
	}
	public EChartsStepLineDto(String name,List<String> legendData,List<String> xAxisName,List<String> snames,List<String> stepNames,List<List<String>> datas) {
		title.put("text", name);
		tooltip.put("trigger", "axis");
		
		legend.put("data", legendData);
		
		grid.put("left", "3%");
		grid.put("right", "4%");
		grid.put("bottom", "3%");
		grid.put("containLabel", true);
		
		xAxis.put("type", "category");
		xAxis.put("data", xAxisName);
		yAxis.put("type", "value");
		
		for(int i=0;i<snames.size();i++) {
			EChartsSeries eChartsSeries = new EChartsSeries(snames.get(i), "line");
			eChartsSeries.setStep(stepNames.get(i));
			eChartsSeries.setData(datas.get(i));
			series.add(eChartsSeries);
		}
		
		
	}
	public Map<String, Object> getTitle() {
		return title;
	}
	public void setTitle(Map<String, Object> title) {
		this.title = title;
	}
	public Map<String, Object> getTooltip() {
		return tooltip;
	}
	public void setTooltip(Map<String, Object> tooltip) {
		this.tooltip = tooltip;
	}
	public Map<String, List<String>> getLegend() {
		return legend;
	}
	public void setLegend(Map<String, List<String>> legend) {
		this.legend = legend;
	}
	public Map<String, Object> getGrid() {
		return grid;
	}
	public void setGrid(Map<String, Object> grid) {
		this.grid = grid;
	}
	public Map<String, Object> getxAxis() {
		return xAxis;
	}
	public void setxAxis(Map<String, Object> xAxis) {
		this.xAxis = xAxis;
	}
	public Map<String, Object> getyAxis() {
		return yAxis;
	}
	public void setyAxis(Map<String, Object> yAxis) {
		this.yAxis = yAxis;
	}
	public List<EChartsSeries> getSeries() {
		return series;
	}
	public void setSeries(List<EChartsSeries> series) {
		this.series = series;
	}
	
	/*
	 {
		 "series":[{"data":[{"name":"广告","value":"200"},
		 {"name":"联盟","value":"300"},
		 {"name":"部落","value":"400"}],
		 "roseType":"angle",
		 "name":"123",
		 "itemStyle":{
		 				"emphasis":{
		 							"shadowOffsetX":0,
		 							"shadowOffsetY":0,
		 							"shadowBlur":200,
		 							"shadowColor":"rgba(0, 0, 0, 0.5)"}
		 			    },
		 "radius":"55%",
		 "type":"pie"}]
	 }

	 */
	
	
	
}
