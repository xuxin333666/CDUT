package cn.kgc.service.impl;

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
import cn.kgc.model.Student;
import cn.kgc.service.intf.StudentService;
import cn.kgc.utils.StudentUtils;

public class StudentServiceImpl implements StudentService {
	StudentDao studentpDao = new StudentDaoImpl();
	private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class); 

	@Override
	public int getCount(Map<String, String[]> feilds) throws ServiceException {
		try {
			return studentpDao.getCount(feilds);
		} catch (DaoException e) {
			logger.error("[StudentServiceImpl:getCount]" + e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public PageBean<Student> query(PageBean<Student> pageBean, Map<String, String[]> feilds) throws ServiceException {
		List<Student> students;
		GroupDao groupDao = new GroupDaoImpl();
		try {
			students = studentpDao.query(pageBean,feilds);
			Group group = null;
			for (Student student : students) {
				try {
					group = groupDao.query(student.getGroup().getId());
				} catch (DaoException e) {
					logger.error("[GroupServiceImpl:query]" + e.getMessage());
				}
				student.setGroup(group);
			}
			try {
				students = StudentUtils.listfilter(students);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("[GroupServiceImpl:query:∑¥…‰“Ï≥£]" + e.getMessage());
				throw new ServiceException(e.getMessage());
			}
			pageBean.setDataList(students);
			return pageBean;
		} catch (DaoException e) {
			logger.error("[GroupServiceImpl:query]" + e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

}
