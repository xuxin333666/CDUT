package cn.kgc.dao.intf;

import java.util.List;
import java.util.Map;

import cn.kgc.bean.PageBean;
import cn.kgc.exception.DaoException;
import cn.kgc.model.Professional;

public interface ProfessionalDao {

	int getCount(Map<String, String[]> feilds) throws DaoException;

	List<Professional> query(PageBean<Professional> pageBean, Map<String, String[]> feilds) throws DaoException;

}
