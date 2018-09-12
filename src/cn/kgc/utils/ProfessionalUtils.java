package cn.kgc.utils;

import java.util.List;

import cn.kgc.model.Professional;

public class ProfessionalUtils {
	public static final String professionalCode = "code";
	public static final String professionalSystme = "systme";
	public static final String professionalStatus = "status";
	
	
	public static List<Professional> listfilter(boolean flag,List<Professional> pros) {
		if(flag) {
			for (Professional pro : pros) {
				pro.setCode(code2Str(pro.getCode(),professionalCode));
				pro.setEductionalSystme(code2Str(pro.getEductionalSystme(),professionalSystme));
				pro.setStatus(code2Str(pro.getStatus(),professionalStatus));
			}
		} else {
			for (Professional pro : pros) {
				pro.setCode(str2Code(pro.getCode(),professionalCode));
				pro.setEductionalSystme(str2Code(pro.getEductionalSystme(),professionalSystme));
				pro.setStatus(str2Code(pro.getStatus(),professionalStatus));
			}
		}
		return pros;
	}
	
	
	public static String code2Str(String code,String rule) {
		if(code != null) {
			if(professionalCode.equals(rule)) {
				String str = code;
				code = code.replace("10000", "��ľ����").replace("10001", "��������缼��").replace("10002", "�������").replace("10003", "��Ƶ��㻯");
				return code + "(" + str + ")";
			} else if(professionalSystme.equals(rule)) {
				return code.replace("1", "3����").replace("2", "4����").replace("3", "��˶����").replace("4", "��˶������");
			} else if(professionalStatus.equals(rule)) {
				return code.replace("1", "����").replace("0", "ͣ��");
			}
		}
		return code;
	}
	
	public static String str2Code(String str,String rule) {
		if(str != null) {
			if(professionalCode.equals(rule)) {
				return str.replace("��ľ����","10000").replace("��������缼��","10001" ).replace("�������","10002").replace("��Ƶ��㻯","10003");
			} else if(professionalSystme.equals(rule)) {
				return str.replace("3����","1").replace("4����","2").replace("��˶����","3").replace("��˶������","4");
			} else if(professionalStatus.equals(rule)) {
				return str.replace("����","1").replace("ͣ��","0");
			}
		}
		return str;
	}
}
