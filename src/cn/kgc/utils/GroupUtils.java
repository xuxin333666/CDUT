package cn.kgc.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.kgc.model.Group;

public class GroupUtils {
	public static final String professionalStatus = "status";
	public static final String[] colName = new String[]{"���","�༶���","�༶����","����ʱ��","רҵ","ѧ��","������","״̬"};
	public static final String[] fields = new String[]{"id","name","date","#professional#name","#professional#eductionalSystme","groupManager","status"};
	public static final Map<String, Map<String, String>> selectMap = new HashMap<>();
	
	static {
		Map<String, String> map = new HashMap<>();
		map.put("01", "<span style='color:green'>����</span>");
		map.put("02", "<span style='color:red'>ͣ��</span>");
		selectMap.put(professionalStatus, map);
	}
	
	public static List<Group> listfilter(List<Group> groups) {
		for (Group group : groups) {
			modelfilter(group);
		}
		return groups;
	}
	
	public static Group modelfilter(Group group) {
		group.setStatus(code2Str(group.getStatus(),professionalStatus));
		ProfessionalUtils.modelfilter(group.getProfessional());
		return group;
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

	public static String condition2sql(String string) {
		if("gdate".equals(string)) {
			return "g.date";
		} else if("gname".equals(string)) {
			return "g.name";
		} else {
			return string;
		}
	}
	
}
