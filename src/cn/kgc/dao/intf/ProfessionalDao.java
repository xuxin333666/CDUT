package cn.kgc.dao.intf;

import java.util.List;
import java.util.Map;

import cn.kgc.bean.PageBean;
import cn.kgc.exception.DaoException;
import cn.kgc.model.Professional;

public interface ProfessionalDao {

	int getCount(Map<String, String[]> feilds) throws DaoException;

	List<Professional> query(PageBean<Professional> pageBean, Map<String, String[]> feilds) throws DaoException;

	List<Professional> query() throws DaoException;
	
	List<Professional> query(String cloName,String value) throws DaoException;
	
	Professional query(String id) throws DaoException;
	
	int insert(Professional pro) throws DaoException;

	int update(Professional pro) throws DaoException;


	int deletes(List<String> idArr) throws DaoException;

	int updates(List<String> idArr,String arg) throws DaoException;

	List<Map<String, Object>> statisticalQuery(String sql) throws DaoException;


}
