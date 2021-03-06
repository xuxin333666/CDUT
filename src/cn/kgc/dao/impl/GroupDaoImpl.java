package cn.kgc.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.kgc.bean.PageBean;
import cn.kgc.dao.intf.GroupDao;
import cn.kgc.exception.DaoException;
import cn.kgc.model.Group;
import cn.kgc.model.Professional;
import cn.kgc.utils.DateUtils;
import cn.kgc.utils.GroupUtils;
import cn.kgc.utils.StringUtils;

public class GroupDaoImpl extends BaseDaoImpl<Group> implements GroupDao {
	public static final String[] columnName = {"g.id","g.name","g.date","g.group_manager","g.male_count","g.female_count","g.status","p.id","p.code","p.name","p.name_en","p.date","p.eductional_systme",
			"p.total_score","p.teather_count","p.status"};
	public static final String selectSql = "g.id,g.name,g.date,g.group_manager,g.male_count,g.female_count,g.status,p.id,p.code,p.name,p.name_en,p.date,p.eductional_systme,"
			+ "p.total_score,p.teather_count,p.status FROM t_group AS g LEFT JOIN t_professional AS p ON g.pro_id = p.id WHERE 2=2";
	
	public static final String[] columnName_update = {"id","name","date","group_manager","male_count","female_count"};
	
	
	@Override
	public int getCount(Map<String, String[]> feilds) throws DaoException {
		StringBuilder sb = new StringBuilder();
		List<Object> args = new ArrayList<>();
		sb.append("SELECT count(1) AS c," + selectSql);
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
				if("groupMinDate".equals(key)) {
					key = GroupUtils.condition2sql("gdate");
					sb.append(" AND "+ key +" >= ?");
					args.add(DateUtils.String2Date(value));
				} else if("groupMaxDate".equals(key)) {
					key = GroupUtils.condition2sql("gdate");
					sb.append(" AND "+ key +" <= ?");
					args.add(DateUtils.String2Date(value));
				} else {
					key = GroupUtils.condition2sql(key);
					sb.append(" AND " + key + " LIKE ?");
					args.add("%" + value + "%");
				}
			}
		}
		
		return sb;
		
	}


	@Override
	public List<Group> query(PageBean<Group> pageBean, Map<String, String[]> feilds) throws DaoException {
		StringBuilder sb = new StringBuilder();
		List<Object> args = new ArrayList<>();
		sb.append("SELECT " + selectSql);
		sb = createSb(sb, feilds,args);
		sb.append(" ORDER BY g.id + 0 LIMIT ?,?");
		return queryByConditions(sb.toString(), Group.class , Professional.class, columnName, args,pageBean.getCurrentPage(),pageBean.getCountPerPage());
	}


	@Override
	public Group query(String id) throws DaoException {
		List<Group> groups = queryById("SELECT " + selectSql + " AND g.id=?", Group.class, Professional.class, columnName, id);
		if(groups.size() != 0) {
			return groups.get(0);
		} else {
			throw new DaoException("�Ҳ����ð༶");
		}
	}


	@Override
	public int insert(Group group) throws DaoException {
		return insert("INSERT INTO t_group VALUES (?,?,?,?,?,?,'01',?)", group, 0, 5,group.getProfessional().getId());
	}


	@Override
	public int update(Group group) throws DaoException {
		return updateById("UPDATE t_group SET ", group, columnName_update, group.getId());
	}


	@Override
	public int deletes(List<String> idArr) throws DaoException {
		return deleteById("DELETE FROM t_group WHERE id = ?", idArr);
	}


	@Override
	public List<Group> query(String key, String value) throws DaoException {
		return query("SELECT * FROM t_group as g LEFT JOIN t_professional as p on g.pro_id = p.id WHERE " + key + " = "+ value +" ORDER BY g.id + 0", Group.class, Professional.class, columnName);
	}
	
	@Override
	public List<Group> query(Map<String, String[]> keys) throws DaoException {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT " + selectSql);
		List<Object> values = new ArrayList<>();
		sb = createSb(sb, keys,values);
		sb.append(" ORDER BY g.id + 0");
		return queryByConditions(sb.toString(), Group.class , Professional.class, columnName, values);
	}


	@Override
	public int updates(List<String> idArr, String arg) throws DaoException {
		return updatesById("UPDATE t_group SET ",idArr, "status", arg);
	}
}
