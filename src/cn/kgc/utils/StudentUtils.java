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
	public static final String[] colName = new String[]{"���","����","�Ա�","ѧ��","��ѧ����","רҵ","�༶","ѧ��"};
	public static final String[] fields = new String[]{"name","gender","id","admissionDate","#group#professional#name","#group#name","#group#professional#eductionalSystme"};
	public static final String[] colName_report = new String[]{"���","����","�Ա�","ѧ��","�༶","����״̬","��������","ע��״̬","�Ƿ����","�Ƿ�סУ"};
	public static final String[] fields_report = new String[]{"name","gender","id","#group#name","reportStatus","reportDate","registStatus","stadyStatus","residenceStatus"};
	public static final String[] colName_regist = new String[]{"���","����","�Ա�","ѧ��","�༶","ע��״̬","ע������","����״̬","�Ƿ����","�Ƿ�סУ"};
	public static final String[] fields_regist = new String[]{"name","gender","id","#group#name","registStatus","registDate","reportStatus","stadyStatus","residenceStatus"};
	public static final Map<String, Map<String, String>> selectMap = new HashMap<>();
	
	static {
		Map<String, String> map = new HashMap<>();
		map.put("01", "��");
		map.put("02", "Ů");
		selectMap.put(gender, map);
		
		map = new HashMap<>();
		map.put("01", "����");
		map.put("02", "����");
		map.put("03", "ά�����");
		map.put("04", "����");
		selectMap.put(national, map);
		
		map = new HashMap<>();
		map.put("01", "���֤");
		map.put("02", "����");
		map.put("03", "���ڲ�");
		selectMap.put(idcardType, map);
		
		map = new HashMap<>();
		map.put("01", "��ס����");
		map.put("02", "��ʱ����");
		map.put("03", "���廧��");
		selectMap.put(registeredType, map);
		
		map = new HashMap<>();
		map.put("01", "A��");
		map.put("02", "B��");
		map.put("03", "O��");
		map.put("04", "AB��");
		selectMap.put(bloodType, map);
		
		map = new HashMap<>();
		map.put("01", "����");
		map.put("02", "����");
		map.put("03", "Сѧ");
		map.put("04", "��ѧ");
		selectMap.put(educationBackground, map);
		
		map = new HashMap<>();
		map.put("01", "<span style='color:green'>�ڶ�</span>");
		map.put("02", "<span style='color:orange'>���</span>");
		map.put("03", "<span style='color:red'>��ѧ</span>");
		selectMap.put(stadyStatus, map);
		
		map = new HashMap<>();
		map.put("01", "δ��");
		map.put("02", "�ѻ�");
		map.put("03", "����");
		selectMap.put(maritalStatus, map);
		
		map = new HashMap<>();
		map.put("01", "����");
		map.put("02", "�ϸ�");
		map.put("03", "���ϸ�");
		selectMap.put(healthStatus, map);
		
		map = new HashMap<>();
		map.put("01", "��Ա");
		map.put("02", "��Ա");
		map.put("03", "���ȶ�Ա");
		map.put("04", "Ⱥ��");
		selectMap.put(politicalStatus, map);
		
		map = new HashMap<>();
		map.put("01", "<span style='color:green'>�ѱ���</span>");
		map.put("02", "<span style='color:red'>δ����</span>");
		selectMap.put(reportStatus, map);
		
		map = new HashMap<>();
		map.put("01", "סУ");
		map.put("02", "�߶�");
		selectMap.put(residenceStatus, map);
		
		map = new HashMap<>();
		map.put("01", "<span style='color:green'>��ע��</span>");
		map.put("02", "<span style='color:red'>δע��</span>");
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
