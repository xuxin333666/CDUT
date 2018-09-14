package cn.kgc.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.kgc.model.Professional;

public class ProfessionalUtils {
	public static final String professionalCode = "code";
	public static final String professionalSystme = "eductionalSystme";
	public static final String professionalStatus = "status";
	public static final String[] colName = new String[]{"���","רҵ���","רҵ����","רҵ����","רҵӢ����","��������","ѧ��","��ѧ��","��ʦ����","״̬"};
	public static final String[] fields = new String[]{"id","name","code","nameEn","date","eductionalSystme","totalScore","teatherCount","status"};
	public static final Map<String, Map<String, String>> selectMap = new HashMap<>();
	
	static {
		Map<String, String> map = new HashMap<>();
		map.put("10000", "��ľ����(10000)");
		map.put("10001", "��������缼��(10001)");
		map.put("10002", "�������(10002)");
		map.put("10003", "��Ƶ��㻯(10003)");
		selectMap.put(professionalCode, map);
		
		map = new HashMap<>();
		map.put("01", "3����");
		map.put("02", "4����");
		map.put("03", "��˶����");
		map.put("04", "��˶������");
		selectMap.put(professionalSystme, map);
		
		map = new HashMap<>();
		map.put("01", "<span style='color:green'>����</span>");
		map.put("02", "<span style='color:red'>ͣ��</span>");
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
