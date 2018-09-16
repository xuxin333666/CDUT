package cn.kgc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.kgc.bean.PageBean;
import cn.kgc.dao.impl.GroupDaoImpl;
import cn.kgc.dao.impl.StudentDaoImpl;
import cn.kgc.dao.intf.GroupDao;
import cn.kgc.dao.intf.StudentDao;
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
		int status = 0;
		List<String> errorList = checkStudentExist(idArr);
		try {
			status =  groupDao.deletes(idArr);
		} catch (DaoException e) {
			logger.error("[GroupServiceImpl:deletes]" + e.getMessage());
			throw new ServiceException(e.getMessage());
		}
		if(errorList.size() != 0 ) {
			StringBuilder sb = new StringBuilder();
			sb.append("如下班级无法删除，请检查是否含有学生：\n");
			for (String id : errorList) {
				sb.append("班级编号： " + id + "\n");
			}
			throw new ServiceException(sb.toString());
		}
		return status;
	}

	@Override
	public int enable(List<String> idArr) throws ServiceException {
		try {
			return groupDao.updates(idArr,"01");
		} catch (DaoException e) {
			logger.error("[GroupServiceImpl:enable]" + e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public int disable(List<String> idArr) throws ServiceException {
		int status = 0;
		List<String> errorList = checkStudentExist(idArr);
		try {
			status = groupDao.updates(idArr, "02");
		} catch (DaoException e) {
			logger.error("[GroupServiceImpl:disable]" + e.getMessage());
			throw new ServiceException(e.getMessage());
		}
		
		if(errorList.size() != 0 ) {
			StringBuilder sb = new StringBuilder();
			sb.append("如下班级无法停用，请检查是否含有学生：\n");
			for (String id : errorList) {
				sb.append("班级编号： " + id + "\n");
			}
			throw new ServiceException(sb.toString());
		}
		return status;
	}

	
	private List<String> checkStudentExist(List<String> idArr) throws ServiceException {
		List<String> errorList = new ArrayList<>();
		Map<String, String[]> maps = new HashMap<>();
		StudentDao studentDao = new StudentDaoImpl();
		
		for (String id : idArr) {
			maps.put("g.id", new String[]{id});
			try {
				int count = studentDao.getCount(maps);
				if(count != 0) {
					errorList.add(id);
				}
			} catch (DaoException e) {
				logger.error("[GroupServiceImpl:checkStudentExist]" + e.getMessage());
				throw new ServiceException(e.getMessage());
			}
		}
		idArr.removeAll(errorList);
		return errorList;
	}
}
