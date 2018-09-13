package cn.kgc.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.kgc.bean.PageBean;
import cn.kgc.dao.impl.GroupDaoImpl;
import cn.kgc.dao.intf.GroupDao;
import cn.kgc.exception.DaoException;
import cn.kgc.exception.ServiceException;
import cn.kgc.model.Group;
import cn.kgc.service.intf.GroupService;
import cn.kgc.utils.GroupUtils;
import cn.kgc.utils.StringUtils;

public class GroupServiceImpl implements GroupService {
	GroupDao groupDao = new GroupDaoImpl();
	private static final Logger logger = LoggerFactory.getLogger(GroupServiceImpl.class); 
	@Override
	public int getCount(Map<String, String[]> feilds) throws ServiceException {
		try {
			return groupDao.getCount(feilds);
		} catch (DaoException e) {
			logger.error("[GroupServiceImpl:getCount]" + e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public PageBean<Group> query(PageBean<Group> pageBean, Map<String, String[]> feilds)
			throws ServiceException {
		List<Group> groups;
		try {
			groups = groupDao.query(pageBean,feilds);
			groups = GroupUtils.listfilter(groups);
			pageBean.setDataList(groups);
			return pageBean;
		} catch (DaoException e) {
			logger.error("[GroupServiceImpl:query]" + e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public String getNewId() {
		return StringUtils.createTimeRandomId();
	}

	@Override
	public Group query(String id) throws ServiceException {
		try {
			return groupDao.query(id);
		} catch (DaoException e) {
			logger.error("[GroupServiceImpl:query]" + e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	
	@Override
	public int add(Group group) throws ServiceException {
		try {
			return groupDao.insert(group);
		} catch (DaoException e) {
			logger.error("[GroupServiceImpl:add]" + e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public int modify(Group group) throws ServiceException {
		try {
			return groupDao.update(group);
		} catch (DaoException e) {
			logger.error("[GroupServiceImpl:modify]" + e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public int deletes(List<String> idArr) throws ServiceException {
		try {
			return groupDao.deletes(idArr);
		} catch (DaoException e) {
			logger.error("[GroupServiceImpl:deletes]" + e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

}
