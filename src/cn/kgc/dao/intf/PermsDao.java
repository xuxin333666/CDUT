package cn.kgc.dao.intf;

import java.util.List;
import java.util.Set;

import cn.kgc.exception.DaoException;
import cn.kgc.model.Perms;
import cn.kgc.model.User;

public interface PermsDao {

	List<Perms> query() throws DaoException;

	Set<String> queryPermsName(User user) throws DaoException;

	List<Perms> queryPermsName(String pid) throws DaoException;

	Perms query(String id) throws DaoException;

}
