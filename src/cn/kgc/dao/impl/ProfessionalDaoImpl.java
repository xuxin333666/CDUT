package cn.kgc.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


import cn.kgc.bean.PageBean;
import cn.kgc.dao.intf.ProfessionalDao;
import cn.kgc.exception.DaoException;
import cn.kgc.model.Professional;
import cn.kgc.utils.DateUtils;
import cn.kgc.utils.StringUtils;

public class ProfessionalDaoImpl extends BaseDaoImpl<Professional> implements ProfessionalDao {
	public static final String[] columnName = {"id","code","name","name_en","date","eductional_systme",
			"total_score","teather_count","status"};
	public static final String[] columnName_update = {"id","code","name","name_en","date","eductional_systme",
			"total_score","teather_count"};
	
	@Override
	public int getCount(Map<String, String[]> feilds) throws DaoException {
		StringBuilder sb = new StringBuilder();
		List<Object> args = new ArrayList<>();
		sb.append("SELECT count(1) AS c FROM t_professional WHERE 2=2");
		sb = createSb(sb, feilds,args);
		return getCount(sb.toString(), "c", args);
	}

	@Override
	public List<Professional> query(PageBean<Professional> pageBean, Map<String, String[]> feilds) throws DaoException {
		StringBuilder sb = new StringBuilder();
		List<Object> args = new ArrayList<>();
		sb.append("SELECT * FROM t_professional WHERE 2=2");
		sb = createSb(sb, feilds,args);
		sb.append(" ORDER BY id + 0 LIMIT ?,?");
		return queryByConditions(sb.toString(), Professional.class , null, columnName, args,pageBean.getCurrentPage(),pageBean.getCountPerPage());
		
	}
	
	
	private StringBuilder createSb(StringBuilder sb,Map<String, String[]> feilds,List<Object> args) {
		Set<String> keys = feilds.keySet();
		Iterator<String> it = keys.iterator();
		
		while(it.hasNext()) {
			String key = it.next();
			String value = feilds.get(key)[0];
			if(StringUtils.isNotEmpty(value) && !"page".equals(key)) {
				if("proMinDate".equals(key)) {
					sb.append(" AND date >= ?");
					args.add(DateUtils.String2Date(value));
				} else if("proMaxDate".equals(key)) {
					sb.append(" AND date <= ?");
					args.add(DateUtils.String2Date(value));
				} else {
					sb.append(" AND " + key + " LIKE ?");
					args.add("%" + value + "%");
				}
			}
		}
		
		return sb;
		
	}

	@Override
	public int insert(Professional pro) throws DaoException {
		return insert("INSERT INTO t_professional VALUES (?,?,?,?,?,?,?,?,1)", pro, 0, 7);
	}

	@Override
	public int update(Professional pro) throws DaoException {
		return updateById("UPDATE t_professional SET ", pro, columnName_update, pro.getId());
	}

	@Override
	public Professional query(String id) throws DaoException {
		List<Professional> professionals = queryById("SELECT * FROM t_professional WHERE id=?", Professional.class, null, columnName, id);
		if(professionals.size() != 0) {
			return professionals.get(0);
		} else {
			throw new DaoException("找不到该专业");
		}
	}

	@Override
	public int deletes(List<String> idArr) throws DaoException {
		return deleteById("DELETE FROM t_professional WHERE id = ?", idArr);
	}

	@Override
	public int updates(List<String> idArr,String arg) throws DaoException {
		return super.updateById("UPDATE t_professional SET status=? WHERE id=?", idArr, arg);
	}


}
