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
				code = code.replace("10000", "土木工程").replace("10001", "计算机网络技术").replace("10002", "财务管理").replace("10003", "会计电算化");
				return code + "(" + str + ")";
			} else if(professionalSystme.equals(rule)) {
				return code.replace("1", "3年制").replace("2", "4年制").replace("3", "本硕连读").replace("4", "本硕博连读");
			} else if(professionalStatus.equals(rule)) {
				return code.replace("1", "启用").replace("0", "停用");
			}
		}
		return code;
	}
	
	public static String str2Code(String str,String rule) {
		if(str != null) {
			if(professionalCode.equals(rule)) {
				return str.replace("土木工程","10000").replace("计算机网络技术","10001" ).replace("财务管理","10002").replace("会计电算化","10003");
			} else if(professionalSystme.equals(rule)) {
				return str.replace("3年制","1").replace("4年制","2").replace("本硕连读","3").replace("本硕博连读","4");
			} else if(professionalStatus.equals(rule)) {
				return str.replace("启用","1").replace("停用","0");
			}
		}
		return str;
	}
}
