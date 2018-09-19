package cn.kgc.dao.impl;

import java.util.List;
import java.util.Set;

import cn.kgc.dao.intf.PermsDao;
import cn.kgc.exception.DaoException;
import cn.kgc.model.Perms;
import cn.kgc.model.User;

public class PermsDaoImpl extends BaseDaoImpl<Perms> implements PermsDao {
	public static final String[] columnName = {"s.id","s.name","s.icon_name","s.type","s.date",
			"s.tag_name","s.data_url","p.id"};
	@Override
	public List<Perms> query() throws DaoException {
		return query("SELECT s.*,p.* FROM t_perms AS s LEFT JOIN t_perms AS p ON s.p_id = p.id WHERE s.type != '03'", Perms.class, Perms.class, columnName);
	}
	@Override
	public Set<String> queryPermsName(User user) throws DaoException {
		return queryPermsName("select p.tag_name from (t_role_perms as rp join t_perms as p on rp.perms_id = p.id) join (t_user_role as ur join t_role as r on ur.role_id = r.id) on rp.role_id = r.id  where ur.user_id = ?", user.getUsername(), "p.tag_name");
	}
	@Override
	public List<Perms> queryPermsName(String pid) throws DaoException {
		if(pid == null) {
			return query("SELECT * FROM t_perms AS s LEFT JOIN t_perms AS p ON s.p_id = p.id WHERE p.id is NULL", Perms.class, Perms.class, columnName);
		} else {
			return queryById("SELECT * FROM t_perms AS s LEFT JOIN t_perms AS p ON s.p_id = p.id WHERE p.id = ?", Perms.class, Perms.class, columnName, pid);
		}
	}


}
