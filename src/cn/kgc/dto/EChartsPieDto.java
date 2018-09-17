package cn.kgc.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.kgc.model.EChartsSeries;

public class EChartsPieDto {
	private List<EChartsSeries> series = new ArrayList<>();
	
	

	public EChartsPieDto(String name,List<Map<String, String>> data) {
		EChartsSeries pieSeries = new EChartsSeries();
		pieSeries.setType("pie");
		pieSeries.setRadius("55%");
		pieSeries.setRoseType("angle");
		pieSeries.setData(data);
		pieSeries.setName(name);
		series.add(pieSeries);
	}

	public List<EChartsSeries> getSeries() {
		return series;
	}

	public void setSeries(List<EChartsSeries> series) {
		this.series = series;
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
