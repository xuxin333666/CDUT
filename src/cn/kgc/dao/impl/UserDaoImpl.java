package cn.kgc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authc.UnknownAccountException;

import cn.kgc.dao.intf.UserDao;
import cn.kgc.exception.DaoException;
import cn.kgc.model.User;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
	private final static String[] columnName = new String[]{"username","password","realName","status","date"};
	
	@Override
	public List<User> queryByNameAndPwd(String username, String password) throws DaoException {
		List<Object> args = new ArrayList<>();
		args.add(username);
		args.add(password);
		return queryByConditions("SELECT * FROM t_user WHERE username = ? AND password = ?", User.class, null, columnName, args);
	}

	@Override
	public int update(String username, String password) throws DaoException {
		return updateById("UPDATE t_user SET password=? WHERE username=?", username, password);
	}

	public User query(String username) throws DaoException,UnknownAccountException {
		List<User> users = queryById("SELECT * FROM t_user WHERE username=?", User.class, null, columnName, username);
		if(users.size() != 0) {
			return users.get(0);
		} else {
			throw new UnknownAccountException();
		}
	}
}
