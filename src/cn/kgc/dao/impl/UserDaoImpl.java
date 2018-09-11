package cn.kgc.dao.impl;

import java.util.List;

import cn.kgc.dao.intf.UserDao;
import cn.kgc.exception.DaoException;
import cn.kgc.model.User;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
	private final static String[] columnName = new String[]{"username","password","realName","status","date"};
	
	@Override
	public List<User> queryByNameAndPwd(String username, String password) throws DaoException {
		return queryByConditions("SELECT * FROM t_user WHERE username = ? AND password = ?", User.class, null, columnName, new String[]{username,password});
	}

	@Override
	public int update(String username, String password) throws DaoException {
		return updateById("UPDATE t_user SET password=? WHERE username=?", username, password);
	}
}
