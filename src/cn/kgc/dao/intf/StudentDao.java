package cn.kgc.dao.intf;

import java.util.List;
import java.util.Map;

import cn.kgc.bean.PageBean;
import cn.kgc.exception.DaoException;
import cn.kgc.model.Student;

public interface StudentDao {

	int getCount(Map<String, String[]> feilds) throws DaoException;

	List<Student> query(PageBean<Student> pageBean, Map<String, String[]> feilds) throws DaoException;

	Student query(String id) throws DaoException;

	int insert(Student student) throws DaoException;

	int update(Student student) throws DaoException;

	int update(String key, List<Object> args) throws DaoException;

	int updatesById(List<String> idArr, Map<String, Object> argMap) throws DaoException;

}
