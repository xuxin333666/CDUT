package cn.kgc.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.kgc.model.EChartsSeries;

public class EChartsBarDto {
	private Map<String, Object> title = new HashMap<>();
	private Map<String, Object> tooltip = new HashMap<>();
	private Map<String, List<String>> legend = new HashMap<>();
	private Map<String, Object> xAxis = new HashMap<>();
	private Map<String, Object> yAxis = new HashMap<>();
	private List<EChartsSeries> series = new ArrayList<>();
	
	public EChartsBarDto(String name,List<String> legendNames,List<String> xAxisData,List<String> snames,List<List<String>> datas) {
		title.put("text", name);
		List<String> temp = null;
		temp = new ArrayList<>();
		for(int i=0;i<legendNames.size();i++) {
			temp.add(legendNames.get(i));
		}
		legend.put("data",temp);
		xAxis.put("data", xAxisData);
		for(int i=0;i<snames.size();i++){
			EChartsSeries eChartsSeries = new EChartsSeries(snames.get(i), "bar");
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

	public Map<String, List<String>> getLegend() {
		return legend;
	}

	public void setLegend(Map<String, List<String>> legend) {
		this.legend = legend;
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


	public Map<String, Object> getTooltip() {
		return tooltip;
	}


	public void setTooltip(Map<String, Object> tooltip) {
		this.tooltip = tooltip;
	}
	
	/*
	{"tooltip":{},
		"yAxis":{},
		"xAxis":{"data":["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]},
		"title":{"text":"ECharts 入门示例"},"legend":{"data":["销量","销量","销量"]},
		"series":[{"data":["100","200","150","300","120","110"],
				"name":"销量",
				"itemStyle":{},
				"type":"bar"},{"data":["100","200","150","300","120","110"],
				"name":"销量",
				"itemStyle":{},
				"type":"bar"},{"data":["100","200","150","300","120","110"],
				"name":"销量",
				"itemStyle":{},
				"type":"bar"}
		]
	}
	{"tooltip":{},
		"yAxis":{},
		"xAxis":{"data":["java高级","会计学","岩土工程","建筑工程","没什么名字好起","用友培训班"]},
		"title":{"text":"各专业学生人数统计柱状图"},"legend":{"data":["人数","男生人数","女生人数"]},
		"series":[{"data":["3","1","0","1","0","0"],
			"name":"人数",
			"itemStyle":{},
			"type":"bar"},{"data":["2","1","0","1","0","0"],
			"name":"男生人数",
			"itemStyle":{},
			"type":"bar"},{"data":["1","0","0","0","0","0"],
			"name":"女生人数",
			"itemStyle":{},
			"type":"bar"}]
	}


	*/

	
}
