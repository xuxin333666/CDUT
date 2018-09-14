package cn.kgc.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.kgc.model.Professional;

public class ProfessionalUtils {
	public static final String professionalCode = "code";
	public static final String professionalSystme = "eductionalSystme";
	public static final String professionalStatus = "status";
	public static final String[] colName = new String[]{"序号","专业编号","专业名称","专业代码","专业英文名","创建日期","学制","总学分","教师人数","状态"};
	public static final String[] fields = new String[]{"id","name","code","nameEn","date","eductionalSystme","totalScore","teatherCount","status"};
	public static final Map<String, Map<String, String>> selectMap = new HashMap<>();
	
	static {
		Map<String, String> map = new HashMap<>();
		map.put("10000", "土木工程(10000)");
		map.put("10001", "计算机网络技术(10001)");
		map.put("10002", "财务管理(10002)");
		map.put("10003", "会计电算化(10003)");
		selectMap.put(professionalCode, map);
		
		map = new HashMap<>();
		map.put("01", "3年制");
		map.put("02", "4年制");
		map.put("03", "本硕连读");
		map.put("04", "本硕博连读");
		selectMap.put(professionalSystme, map);
		
		map = new HashMap<>();
		map.put("01", "<span style='color:green'>启用</span>");
		map.put("02", "<span style='color:red'>停用</span>");
		selectMap.put(professionalStatus, map);
	}
	public static List<Professional> listfilter(List<Professional> pros) {
		for (Professional pro : pros) {
			modelfilter(pro);
		}
		return pros;
	}
	
	public static Professional modelfilter(Professional pro) {
		if(pro != null) {
			pro.setCode(code2Str(pro.getCode(),professionalCode));
			pro.setEductionalSystme(code2Str(pro.getEductionalSystme(),professionalSystme));
			pro.setStatus(code2Str(pro.getStatus(),professionalStatus));
		}
		return pro;
	}
	
	
	public static String code2Str(String code,String rule) {
		if(code != null) {
			Map<String,String> strs = selectMap.get(rule);
			if(strs != null) {
				String str = strs.get(code);
				return (str != null) ? str : code;
			}
		}
		return code;
	}
	
}
