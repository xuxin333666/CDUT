package cn.kgc.service.intf;

import java.util.List;
import java.util.Map;

import cn.kgc.bean.PageBean;
import cn.kgc.dto.treeNode;
import cn.kgc.exception.ServiceException;
import cn.kgc.model.Student;

public interface StudentService {

	int getCount(Map<String, String[]> feilds) throws ServiceException;

	PageBean<Student> query(PageBean<Student> pageBean, Map<String, String[]> feilds) throws ServiceException;

	List<treeNode> createTreeNode(String gid) throws ServiceException;

	Student query(String id) throws ServiceException;

	Student getNewStudentByGid(String gid) throws ServiceException;

	int add(Student student) throws ServiceException;

	int modify(Student student) throws ServiceException;

}
