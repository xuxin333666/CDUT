package cn.kgc.service.intf;

import java.util.Map;

import cn.kgc.bean.PageBean;
import cn.kgc.exception.ServiceException;
import cn.kgc.model.Student;

public interface StudentService {

	int getCount(Map<String, String[]> feilds) throws ServiceException;

	PageBean<Student> query(PageBean<Student> pageBean, Map<String, String[]> feilds) throws ServiceException;

}
