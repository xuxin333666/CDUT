package cn.kgc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.kgc.bean.PageBean;
import cn.kgc.dao.impl.GroupDaoImpl;
import cn.kgc.dao.impl.ProfessionalDaoImpl;
import cn.kgc.dao.impl.StudentDaoImpl;
import cn.kgc.dao.intf.GroupDao;
import cn.kgc.dao.intf.ProfessionalDao;
import cn.kgc.dao.intf.StudentDao;
import cn.kgc.dto.treeNode;
import cn.kgc.exception.DaoException;
import cn.kgc.exception.ServiceException;
import cn.kgc.model.Group;
import cn.kgc.model.Professional;
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
				group = groupDao.query(student.getGroup().getId());
				student.setGroup(group);
			}
			try {
				students = StudentUtils.listfilter(students);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("[StudentServiceImpl:query:反射异常]" + e.getMessage());
				throw new ServiceException(e.getMessage());
			}
			pageBean.setDataList(students);
			return pageBean;
		} catch (DaoException e) {
			logger.error("[StudentServiceImpl:query]" + e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<treeNode> createTreeNode(String gid) throws ServiceException {
		ProfessionalDao proDao = new ProfessionalDaoImpl();
		GroupDao groupDao = new GroupDaoImpl();
		List<treeNode> proNodes = new ArrayList<>();
		try {
			List<Professional> pros = proDao.query("status", "01");
			for (Professional professional : pros) {
				treeNode pronode = new treeNode();
				pronode.setText(professional.getName());
				List<treeNode> groupNodes = new ArrayList<>();
				pronode.setNodes(groupNodes);
				proNodes.add(pronode);
				try {
					Map<String, String[]> keys = new HashMap<>();
					keys.put("g.status", new String[]{"01"});
					keys.put("g.pro_id", new String[]{professional.getId()});
					List<Group> groups = groupDao.query(keys);
					for (Group group : groups) {
						treeNode groupnode = new treeNode();
						groupnode.setText("<span style='color:red' onclick='groupTreeClick(\""+ group.getId() +"\")'>"+ group.getName() +"</span>");
						groupNodes.add(groupnode);
						if(gid != null && gid.equals(group.getId())) {
							Map<String, String> state = new HashMap<>();
							state.put("checked", "true");
							state.put("expanded", "true");
							state.put("selected", "true");
							groupnode.setState(state);
							
							state = new HashMap<>();
							state.put("expanded", "true");
							pronode.setState(state);
						}
					}
				} catch (DaoException e) {
					logger.error("[StudentServiceImpl:createTreeNode:班级查询失败]" + e.getMessage());
				}
				
			}
		} catch (DaoException e) {
			logger.error("[StudentServiceImpl:createTreeNode:专业查询失败]" + e.getMessage());
		}
		
		return proNodes;
	}

	@Override
	public Student query(String id) throws ServiceException {
		GroupDao groupDao = new GroupDaoImpl();
		Group group = null;
		try {
			Student student = studentpDao.query(id);
			group = groupDao.query(student.getGroup().getId());
			student.setGroup(group);
			return student;
		} catch (DaoException e) {
			logger.error("[StudentServiceImpl:query]" + e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public Student getNewStudentByGid(String gid) throws ServiceException {
		GroupDao groupDao = new GroupDaoImpl();
		try {
			Group group = groupDao.query(gid);
			Student student = new Student();
			student.setGroup(group);
			return student;
		} catch (DaoException e) {
			logger.error("[StudentServiceImpl:getNewStudentByGid]" + e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public int add(Student student) throws ServiceException {
		try {
			studentpDao.query(student.getId());
			throw new ServiceException("该学号已经被使用，请更换");
		} catch (DaoException e1) {
			try {
				return studentpDao.insert(student);
			} catch (DaoException e) {
				logger.error("[StudentServiceImpl:add]" + e.getMessage());
				throw new ServiceException(e.getMessage());
			}
		}
	}

	@Override
	public int modify(Student student) throws ServiceException {
		try {
			return studentpDao.update(student);
		} catch (DaoException e) {
			logger.error("[StudentServiceImpl:modify]" + e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

}
