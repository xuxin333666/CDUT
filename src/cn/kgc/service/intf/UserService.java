package cn.kgc.service.intf;

import cn.kgc.exception.ServiceException;
import cn.kgc.model.User;

public interface UserService {

	User queryByNameAndPwd(String username, String password) throws ServiceException;

	int modifyPwd(String username, String password) throws ServiceException;

}
