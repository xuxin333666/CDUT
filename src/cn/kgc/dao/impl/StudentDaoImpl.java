package cn.kgc.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.kgc.bean.PageBean;
import cn.kgc.dao.intf.StudentDao;
import cn.kgc.exception.DaoException;
import cn.kgc.model.Group;
import cn.kgc.model.Student;
import cn.kgc.utils.DateUtils;
import cn.kgc.utils.StringUtils;
import cn.kgc.utils.StudentUtils;

public class StudentDaoImpl extends BaseDaoImpl<Student> implements StudentDao {
	public static final String[] columnName = {"s.id","s.name","s.photo_url","s.gender","s.registration_no","s.registered_residence",
			"s.national","s.idcard_type","s.idcard","s.birthday","s.birthplace","s.native_place","s.registered_type","s.blood_type","s.source_school","s.admission_date","s.education_background","s.stady_status","s.name_en",
			"s.used_name","s.marital_status","s.health_status","s.nationality","s.phone_num","s.political_status","s.email","s.specialty","s.report_status",
			"s.report_date","s.residence_status","s.regist_status","s.regist_date","g.id"};
	public static final String selectSql = "s.id,s.name,s.photo_url,s.gender,s.registration_no,s.registered_residence,"
			+ "s.national,s.idcard_type,s.idcard,s.birthday,g.id,g.name,g.date,g.group_manager,g.male_count,g.female_count,g.status,p.id,p.code,p.name,p.name_en,p.date,p.eductional_systme,"
			+ "p.total_score,p.teather_count,p.status FROM t_group AS g LEFT JOIN t_professional AS p ON g.pro_id = p.id WHERE 2=2";
	
	public static final String[] columnName_update = {"id","name","photo_url","gender","registration_no","registered_residence",
			"national","idcard_type","idcard","birthday","birthplace","native_place","registered_type","blood_type","source_school","admission_date","education_background","stady_status","name_en",
			"used_name","marital_status","health_status","nationality","phone_num","political_status","email","specialty","report_status",
			"report_date","residence_status","regist_status","regist_date"};
	
	@Override
	public int getCount(Map<String, String[]> feilds) throws DaoException {
		StringBuilder sb = new StringBuilder();
		List<Object> args = new ArrayList<>();
		sb.append("SELECT count(1) as c FROM t_student AS s LEFT JOIN ( t_group as g LEFT JOIN t_professional as p on g.pro_id = p.id) on s.group_id = g.id WHERE 2=2");
		sb = createSb(sb, feilds,args);
		return getCount(sb.toString(), "c", args);
	}
	

	private StringBuilder createSb(StringBuilder sb,Map<String, String[]> feilds,List<Object> args) {
		Set<String> keys = feilds.keySet();
		Iterator<String> it = keys.iterator();
		
		while(it.hasNext()) {
			String key = it.next();
			String value = feilds.get(key)[0];
			if(StringUtils.isNotEmpty(value) && !"page".equals(key)) {
				if("studentMinDate".equals(key)) {
					key = StudentUtils.condition2sql("sAdmissionDate");
					sb.append(" AND "+ key +" >= ?");
					args.add(DateUtils.String2Date(value));
				} else if("studentMaxDate".equals(key)) {
					key = StudentUtils.condition2sql("sAdmissionDate");
					sb.append(" AND "+ key +" <= ?");
					args.add(DateUtils.String2Date(value));
				} else {
					key = StudentUtils.condition2sql(key);
					sb.append(" AND " + key + " LIKE ?");
					args.add("%" + value + "%");
				}
			}
		}
		
		return sb;
		
	}


	@Override
	public List<Student> query(PageBean<Student> pageBean, Map<String, String[]> feilds) throws DaoException {
		StringBuilder sb = new StringBuilder();
		List<Object> args = new ArrayList<>();
		sb.append("SELECT * FROM t_student AS s LEFT JOIN ( t_group as g LEFT JOIN t_professional as p on g.pro_id = p.id) on s.group_id = g.id WHERE 2=2");
		sb = createSb(sb, feilds,args);
		sb.append(" ORDER BY s.id + 0 LIMIT ?,?");
		return queryByConditions(sb.toString(), Student.class , Group.class, columnName, args,pageBean.getCurrentPage(),pageBean.getCountPerPage());
	}


	@Override
	public Student query(String id) throws DaoException {
		List<Student> students = queryById("SELECT * FROM t_student AS s LEFT JOIN ( t_group as g LEFT JOIN t_professional as p on g.pro_id = p.id) on s.group_id = g.id WHERE s.id= ?", Student.class, Group.class, columnName, id);
		if(students.size() != 0) {
			return students.get(0);
		} else {
			throw new DaoException("找不到该学生");
		}
	}


	@Override
	public int insert(Student student) throws DaoException {
		return insert("INSERT INTO t_student VALUES (?,?,?,?,?,?,?,?,?,?,"
													+ "?,?,?,?,?,?,?,?,?,?,"
													+ "?,?,?,?,?,?,?,?,?,?,"
													+ "?,?,?)", student, 0, 31,student.getGroup().getId());
	}


	@Override
	public int update(Student student) throws DaoException {
		return updateById("UPDATE t_student SET ", student, columnName_update, student.getId());
	}



}
