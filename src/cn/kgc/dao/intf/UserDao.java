package cn.kgc.dao.intf;

import java.util.List;

import cn.kgc.exception.DaoException;
import cn.kgc.model.User;

public interface UserDao {

	List<User> queryByNameAndPwd(String username, String password) throws Exception;

	int update(String username, String password) throws DaoException;


}
