package cn.kgc.service.intf;

import java.util.List;
import java.util.Map;

import cn.kgc.bean.PageBean;
import cn.kgc.exception.ServiceException;
import cn.kgc.model.Group;

public interface GroupService {

	int getCount(Map<String, String[]> feilds) throws ServiceException;

	PageBean<Group> query(PageBean<Group> pageBean, Map<String, String[]> feilds) throws ServiceException;

	String getNewId();

	Group query(String id) throws ServiceException;

	int add(Group group) throws ServiceException;

	int modify(Group group) throws ServiceException;

	int deletes(List<String> idArr) throws ServiceException;

}
