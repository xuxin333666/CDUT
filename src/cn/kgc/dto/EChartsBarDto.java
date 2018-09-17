package cn.kgc.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.kgc.model.EChartsSeries;

public class EChartsBarDto {
	private Map<String, Object> title = new HashMap<>();
	private Map<String, List<String>> legend = new HashMap<>();
	private Map<String, Object> xAxis = new HashMap<>();
	private Map<String, Object> yAxis = new HashMap<>();
	private List<EChartsSeries> series = new ArrayList<>();
	
	public EChartsBarDto(String name,String legendName,List<String> xAxisData,String sname,List<String> data) {
		title.put("text", name);
		List<String> temp = new ArrayList<>();
		temp.add(legendName);
		legend.put("data",temp);
		xAxis.put("data", xAxisData);
		EChartsSeries eChartsSeries = new EChartsSeries(sname, "bar");
		eChartsSeries.setData(data);
		series.add(eChartsSeries);
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
	
	/*
	{
		"yAxis":{},
		"xAxis":{"data":["����","��ë��","ѩ����","����","�߸�Ь","����"]},
		"title":{"text":"ECharts ����ʾ��"},"legend":{"data":["����"]},
		"series":[{"data":["100","200","150","300","120","110"],
				"name":"����",
				"itemStyle":{},
				"type":"bar"}
		]
	}

	*/

	
}
