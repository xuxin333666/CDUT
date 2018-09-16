package cn.kgc.dao.intf;

import java.util.List;
import java.util.Map;

import cn.kgc.bean.PageBean;
import cn.kgc.exception.DaoException;
import cn.kgc.model.Group;

public interface GroupDao {

	int getCount(Map<String, String[]> feilds) throws DaoException;

	List<Group> query(PageBean<Group> pageBean, Map<String, String[]> feilds) throws DaoException;

	Group query(String id) throws DaoException;

	int insert(Group group) throws DaoException;

	int update(Group group) throws DaoException;

	int deletes(List<String> idArr) throws DaoException;

	List<Group> query(String key, String value) throws DaoException;

	List<Group> query(Map<String, String[]> keys) throws DaoException;

	int updates(List<String> idArr, String string) throws DaoException;

}
