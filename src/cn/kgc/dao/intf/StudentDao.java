package cn.kgc.dao.intf;

import java.util.List;
import java.util.Map;

import cn.kgc.bean.PageBean;
import cn.kgc.exception.DaoException;
import cn.kgc.model.Student;

public interface StudentDao {

	int getCount(Map<String, String[]> feilds) throws DaoException;

	List<Student> query(PageBean<Student> pageBean, Map<String, String[]> feilds) throws DaoException;

}
