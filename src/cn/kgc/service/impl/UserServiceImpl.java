package cn.kgc.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.kgc.dao.impl.UserDaoImpl;
import cn.kgc.dao.intf.UserDao;
import cn.kgc.exception.DaoException;
import cn.kgc.exception.ServiceException;
import cn.kgc.model.User;
import cn.kgc.service.intf.UserService;

public class UserServiceImpl implements UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class); 
	UserDao userDao = new UserDaoImpl();

	@Override
	public User queryByNameAndPwd(String username, String password) throws ServiceException {
		List<User> userList;
		try {
			userList = userDao.queryByNameAndPwd(username,password);
			if(userList.size() != 0) {
				return userList.get(0);
			}
		} catch (Exception e) {
			logger.error("[UserServiceImpl:queryByNameAndPwd]" + e.getMessage());
			throw new ServiceException(e.getMessage());
		}
		return null;
	}

	@Override
	public int modifyPwd(String username, String password) throws ServiceException {
		try {
			return userDao.update(username,password);
		} catch (DaoException e) {
			logger.error("[UserServiceImpl:modifyPwd]" + e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

}
