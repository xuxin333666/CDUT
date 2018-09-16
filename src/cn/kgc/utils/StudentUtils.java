package cn.kgc.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.kgc.model.Student;

public class StudentUtils {
	public static final String gender = "gender";
	public static final String national = "national";
	public static final String idcardType = "idcardType";
	public static final String registeredType = "registeredType";
	public static final String bloodType = "bloodType";
	public static final String educationBackground = "educationBackground";
	public static final String stadyStatus = "stadyStatus";
	public static final String maritalStatus = "maritalStatus";
	public static final String healthStatus = "healthStatus";
	public static final String politicalStatus = "politicalStatus";
	public static final String reportStatus = "reportStatus";
	public static final String residenceStatus = "residenceStatus";
	public static final String registStatus = "registStatus";
	public static final String[] colName = new String[]{"序号","姓名","性别","学号","入学年月","专业","班级","学制"};
	public static final String[] fields = new String[]{"name","gender","id","admissionDate","#group#professional#name","#group#name","#group#professional#eductionalSystme"};
	public static final String[] colName_report = new String[]{"序号","姓名","性别","学号","班级","报道状态","报道日期","注册状态","是否请假","是否住校"};
	public static final String[] fields_report = new String[]{"name","gender","id","#group#name","reportStatus","reportDate","registStatus","stadyStatus","residenceStatus"};
	public static final String[] colName_regist = new String[]{"序号","姓名","性别","学号","班级","注册状态","注册日期","报道状态","是否请假","是否住校"};
	public static final String[] fields_regist = new String[]{"name","gender","id","#group#name","registStatus","registDate","reportStatus","stadyStatus","residenceStatus"};
	public static final Map<String, Map<String, String>> selectMap = new HashMap<>();
	
	static {
		Map<String, String> map = new HashMap<>();
		map.put("01", "男");
		map.put("02", "女");
		selectMap.put(gender, map);
		
		map = new HashMap<>();
		map.put("01", "汉族");
		map.put("02", "回族");
		map.put("03", "维吾尔族");
		map.put("04", "其他");
		selectMap.put(national, map);
		
		map = new HashMap<>();
		map.put("01", "身份证");
		map.put("02", "驾照");
		map.put("03", "户口簿");
		selectMap.put(idcardType, map);
		
		map = new HashMap<>();
		map.put("01", "常住户口");
		map.put("02", "临时户口");
		map.put("03", "集体户口");
		selectMap.put(registeredType, map);
		
		map = new HashMap<>();
		map.put("01", "A型");
		map.put("02", "B型");
		map.put("03", "O型");
		map.put("04", "AB型");
		selectMap.put(bloodType, map);
		
		map = new HashMap<>();
		map.put("01", "本科");
		map.put("02", "高中");
		map.put("03", "小学");
		map.put("04", "中学");
		selectMap.put(educationBackground, map);
		
		map = new HashMap<>();
		map.put("01", "<span style='color:green'>在读</span>");
		map.put("02", "<span style='color:orange'>请假</span>");
		map.put("03", "<span style='color:red'>休学</span>");
		selectMap.put(stadyStatus, map);
		
		map = new HashMap<>();
		map.put("01", "未婚");
		map.put("02", "已婚");
		map.put("03", "单身");
		selectMap.put(maritalStatus, map);
		
		map = new HashMap<>();
		map.put("01", "健康");
		map.put("02", "合格");
		map.put("03", "不合格");
		selectMap.put(healthStatus, map);
		
		map = new HashMap<>();
		map.put("01", "党员");
		map.put("02", "团员");
		map.put("03", "少先队员");
		map.put("04", "群众");
		selectMap.put(politicalStatus, map);
		
		map = new HashMap<>();
		map.put("01", "<span style='color:green'>已报到</span>");
		map.put("02", "<span style='color:red'>未报到</span>");
		selectMap.put(reportStatus, map);
		
		map = new HashMap<>();
		map.put("01", "住校");
		map.put("02", "走读");
		selectMap.put(residenceStatus, map);
		
		map = new HashMap<>();
		map.put("01", "<span style='color:green'>已注册</span>");
		map.put("02", "<span style='color:red'>未注册</span>");
		selectMap.put(registStatus, map);
		
	}
	
	public static List<Student> listfilter(List<Student> students) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		for (Student student : students) {
			modelfilter(student);
		}
		return students;
	}
	
	public static Student modelfilter(Student student) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		if(student != null) {
			Class<?> clazz = student.getClass();
			Set<String> key = selectMap.keySet();
			Iterator<String> it = key.iterator();
			while(it.hasNext()) {
				String rule = it.next();
				Field field = clazz.getDeclaredField(rule);
				field.setAccessible(true);
				Object value = field.get(student);
				if(value != null) {
					String newStr = code2Str(value.toString(),rule);
					field.set(student, newStr);
				}
			}
			GroupUtils.modelfilter(student.getGroup());
		}
		return student;
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
		if("sname".equals(string)) {
			return "s.name";
		} else if("sid".equals(string)) {
			return "s.id";
		} else if("sAdmissionDate".equals(string)) {
			return "s.admission_date";
		} else if("gname".equals(string)) {
			return "g.name";
		} else if("gid".equals(string)) {
			return "g.id";
		} else {
			return string;
		}
	}

}
