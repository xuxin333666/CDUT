package cn.kgc.dao.impl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.kgc.bean.PageBean;
import cn.kgc.exception.DaoException;
import cn.kgc.utils.DBPoolConnection;
import cn.kgc.utils.DateUtils;

public class BaseDaoImpl<T> extends BaseDao2<T> {
	
	protected final String SQL_ERORR = "后台数据错误，";
	
	protected static final Logger logger = LoggerFactory.getLogger(BaseDaoImpl.class); 

	/**
	 * 根据sql语句查询数据，将数据生成对象，放入list返回
	 * @param sql sql语句
	 * @param clazz 要生成的对象的class类
	 * @param subClazz 要生成的对象的子对象的class类
	 * @param columnName 根据该数组一一对应对象的属性
	 * @return 返回List<Object>
	 * @throws Exception
	 */
	protected List<T> query(String sql,Class<?> clazz,Class<?> subClazz,String[] columnName) throws DaoException {
		return queryByConditions(sql, clazz, subClazz, columnName, null,0,0);
	}
	

	/**
	 * 根据sql语句查询数据，将数据生成对象，放入list返回
	 * @param sql sql语句
	 * @param clazz 要生成的对象的class类
	 * @param subClazz 要生成的对象的子对象的class类
	 * @param columnName 根据该数组一一对应对象的属性
	 * @param pageBeanDto 分页对象
	 * @return 返回List<Object>
	 * @throws Exception
	 */
	public List<T> query(String sql, Class<?> clazz, Class<?> subClazz, String[] columnName,
			PageBean<?> pageBeanDto) throws DaoException {
		return queryByConditions(sql, clazz, subClazz, columnName, null,pageBeanDto.getCurrentPage(),pageBeanDto.getCountPerPage());
	}
	
	/**
	 * 根据给定的数据数组查询数据，将数据生成对象，放入list返回
	 * @param sql sql语句
	 * @param clazz 要生成的对象的class类
	 * @param subClazz 要生成的对象的子对象的class类
	 * @param columnName 根据该数组一一对应对象的属性
	 * @param args 根据该数组设置查询参数
	 * @return 返回List<Object>
	 * @throws Exception
	 */
	protected List<T> queryByConditions(String sql,Class<?> clazz,Class<?> subClazz,String[] columnName,List<Object> args) throws DaoException {
		return queryByConditions(sql, clazz, subClazz, columnName, args,0,0);
	}
	
	
	/**
	 * 根据sql语句查询数据,带查询参数的分页查询，将数据生成对象，放入list返回
	 * @param sql sql语句
	 * @param clazz 要生成的对象的class类
	 * @param subClazz 要生成的对象的子对象的class类
	 * @param columnName 根据该数组一一对应对象的属性
	 * @param arg 查询参数
	 * @param pageBeanDto 分页对象
	 * @return 返回List<Object>
	 * @throws Exception
	 */
	public List<T> query(String sql, Class<?> clazz, Class<?> subClazz, String[] columnName,
			String arg, PageBean<?> pageBeanDto) throws DaoException {
		List<Object> args = new ArrayList<>();
		args.add(arg);
		return queryByConditions(sql, clazz, subClazz, columnName, args,pageBeanDto.getCurrentPage(),pageBeanDto.getCountPerPage());
	}
	
	
	/**
	 * 根据给定的数据数组查询数据，将数据生成对象，放入list返回
	 * @param sql sql语句
	 * @param clazz 要生成的对象的class类
	 * @param subClazz 要生成的对象的子对象的class类
	 * @param columnName 根据该数组一一对应对象的属性
	 * @param args 根据该list设置查询参数
	 * @return 返回List<Object>
	 * @throws Exception
	 */
	protected List<T> queryByConditions(String sql,Class<?> clazz,Class<?> subClazz,String[] columnName,List<Object> args,int currentPage,int countPerPage) throws DaoException {
		List<T> list =  new ArrayList<>();
		DBPoolConnection dbp = new DBPoolConnection();
		Connection cn = null;
		PreparedStatement psm = null;
		ResultSet result = null;
		if(countPerPage != 0) {
			if(args == null) {
				args = new ArrayList<>();
			}
			args.add((currentPage-1) * countPerPage);
			args.add(countPerPage);
		}
		try {
			cn = dbp.getConnection();
			psm = cn.prepareStatement(sql);
			prepareStatementSetValue(psm, args);
			result = psm.executeQuery();
			result2List(result,list,clazz,subClazz,columnName);
		} catch (Exception e) {
			logger.error("[BaseDaoImpl:queryByConditions:数据库异常]" + e.getMessage());
			throw new DaoException(SQL_ERORR + e.getMessage());
		} finally {
			if(cn != null) {
				try {
					cn.close();
				} catch (SQLException e) {
					logger.error("[BaseDaoImpl:queryByConditions:数据库连接关闭错误]" + e.getMessage());
					throw new DaoException(SQL_ERORR + e.getMessage());
				}
			}
		}
		return list;
	}
	
	/**
	 * 根据给定的查询数据，将数据生成对象，放入list返回
	 * @param sql sql语句
	 * @param clazz 要生成的对象的class类
	 * @param subClazz 要生成的对象的子对象的class类
	 * @param columnName 根据该数组一一对应对象的属性
	 * @return 返回List<Object>
	 * @throws Exception
	 */
	protected List<T> queryById(String sql,Class<?> clazz,Class<?> subClazz,String[] columnName,String id) throws DaoException {	
		List<Object> args = new ArrayList<>();
		args.add(id);
		return queryByConditions(sql, clazz, subClazz, columnName, args,0,0);
	}
	
	/**
	 * 根据sql语句查询是否存在给定的id
	 * @param sql sql语句
	 *  @param id id
	 * @return 返回是否存在
	 * @throws Exception
	 */
	protected boolean queryIdExist(String sql,String id) throws DaoException {
		DBPoolConnection dbp = new DBPoolConnection();
		Connection cn = null;
		PreparedStatement psm = null;
		ResultSet result = null;
		try {
			cn = dbp.getConnection();
			psm = cn.prepareStatement(sql); 
			psm.setString(1, id);
			result = psm.executeQuery();
			return result.next();
		} catch (Exception e) {
			logger.error("[BaseDaoImpl:queryIdExist:数据库异常]" + e.getMessage());
			throw new DaoException(SQL_ERORR + e.getMessage());
		} finally {
			if(cn != null) {
				try {
					cn.close();
				} catch (SQLException e) {
					logger.error("[BaseDaoImpl:queryIdExist:数据库连接关闭错误]" + e.getMessage());
					throw new DaoException(SQL_ERORR + e.getMessage());
				}
			}
		}
	}
	
	
	/**
	 * 遍历结果集，生成对象，放入list
	 * @param result 查询的结果集
	 * @param list 要放入的容器
	 * @param clazz 要生成的对象的class类
	 * @param subClazz 要生成的对象的子对象的class类
	 * @param columnName 根据该数组一一对应对象的属性
	 * @throws Exception
	 */
	protected void result2List(ResultSet result, List<T> list,Class<?> clazz,Class<?> subClazz,String[] columnName) throws DaoException {
		try {
			while(result.next()) {
				T obj = setValue(result,clazz,subClazz,columnName,0);
				list.add(obj);
			}
		} catch (Exception e) {
			logger.error("[BaseDaoImpl:result2List:数据库异常]" + e.getMessage());
			throw new DaoException(SQL_ERORR + e.getMessage());
		}
	}
	
	/**
	 * 将对象的属性与列名一一对应，从结果集拿出数据赋值到对象对应的属性中
	 * @param result 查询的结果集
	 * @param clazz 要生成的对象的class类
	 * @param subClazz 要生成的对象的子对象的class类
	 * @param columnName 根据该数组一一对应对象的属性
	 * @param index 已经遍历到第几个列名了
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private T setValue(ResultSet result,Class<?> clazz,Class<?> subClazz,String[] columnName,int index) throws DaoException  {
		T obj;
		try {
			obj = (T)clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			logger.error("[BaseDaoImpl:setValue:对象创建实例失败]" + e.getMessage());
			throw new DaoException(SQL_ERORR + e.getMessage());
		}
		try {
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				if(columnName.length == index) {
					return obj;
				}
				field.setAccessible(true);
				Object type = field.getType();
				if(type.equals(String.class)) {
					String str = null;
						str = result.getString(columnName[index++]);
						field.set(obj, str);
				} else if(type.equals(Double.class)) {
					Double db = result.getDouble(columnName[index++]);
					field.set(obj, db);
				} else if(type.equals(java.util.Date.class)) {
					Date db = result.getDate(columnName[index++]);
					field.set(obj, db);
				} else if(type.equals(subClazz)) {
					Object obj2 = setValue(result,subClazz,null,columnName,index);
					field.set(obj, obj2);
				}
			}
		} catch (SQLException e) {
			logger.error("[BaseDaoImpl:setValue:数据库获取数据参数错误]" + e.getMessage());
			throw new DaoException(SQL_ERORR + e.getMessage());
		} catch (IllegalArgumentException | IllegalAccessException e) {
			logger.error("[BaseDaoImpl:setValue:反射设置对象属性值失败]" + e.getMessage());
			throw new DaoException(SQL_ERORR + e.getMessage());
		}
		return obj;
	}
	
	/**
	 * 将给定的对象，添加到数据库
	 * @param sql 添加的sql语句
	 * @param obj 对象的基类
	 * @param attributeStrat 对象的属性从哪里开始与列名一一对应
	 * @param attributeEnd 对象的属性从哪里结束对应
	 * @return 返回影响行数
	 * @throws Exception
	 */
	protected int insert(String sql,Object obj,int attributeStrat,int attributeEnd) throws DaoException {
		return insert(sql, obj, attributeStrat, attributeEnd, new String[]{null});
	}
	
	/**
	 * 将给定的对象（有一个外键id），添加到数据库
	 * @param sql 添加的sql语句
	 * @param obj 对象的基类
	 * @param attributeStrat 对象的属性从哪里开始与列名一一对应
	 * @param attributeEnd 对象的属性从哪里结束对应
	 * @param fkId 外键id的值
	 * @return 返回影响行数
	 * @throws Exception
	 */
	protected int insert(String sql,Object obj,int attributeStrat,int attributeEnd, String fkId) throws DaoException {
		return insert(sql, obj, attributeStrat, attributeEnd, new String[]{fkId});
	}
	
	
	/**
	 *  将给定的对象（有多个外键id），添加到数据库
	 * @param sql 添加的sql语句
	 * @param obj 对象的基类
	 * @param attributeStrat 对象的属性从哪里开始与列名一一对应
	 * @param attributeEnd 对象的属性从哪里结束对应
	 * @param fkIds 外键id的值的数组
	 * @return 返回影响行数
	 * @throws Exception
	 */
	protected int insert(String sql,Object obj,int attributeStrat,int attributeEnd, String[] fkIds) throws DaoException {
		DBPoolConnection dBP = DBPoolConnection.getInstance();
		Connection cn = null;
		PreparedStatement psm = null;
		try {
			cn = dBP.getConnection();
			psm = cn.prepareStatement(sql);
			prepareStatementSetValue(psm, obj, attributeStrat, attributeEnd);
			for (int i = 0; i < fkIds.length; i++) {
				if(fkIds[i] != null) {
					psm.setString(attributeEnd+2+i, fkIds[i]);				
				}				
			}
			return psm.executeUpdate();	
		} catch (Exception e) {
			logger.error("[BaseDaoImpl:insert:数据库错误]" + e.getMessage());
			throw new DaoException(SQL_ERORR + e.getMessage());
		} finally {
			if(cn != null) {
				try {
					cn.close();
				} catch (SQLException e) {
					logger.error("[BaseDaoImpl:insert:数据库连接关闭错误]" + e.getMessage());
					throw new DaoException(SQL_ERORR + e.getMessage());
				}
			}	
		}
	}
	
	
	/**
	 * 将对象的属性一一对应的设置到PreparedStatement的参数中，要求两者顺序一致
	 * @param psm PreparedStatement对象
	 * @param obj 对象的基类
	 * @param attributeStrat 对象的属性从哪里开始与列名一一对应
	 * @param attributeEnd 对象的属性从哪里结束对应
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws SQLException
	 */
	protected void prepareStatementSetValue(PreparedStatement psm,Object obj,int attributeStrat,int attributeEnd) throws Exception {
		Field[] attributes = obj.getClass().getDeclaredFields();
		int index = 1;
		for (;attributeStrat<=attributeEnd;attributeStrat++) {
			attributes[attributeStrat].setAccessible(true);
			Object value = attributes[attributeStrat].get(obj);
			if(value == null) {
				psm.setObject(index++, null);
				continue;
			}
			Class<?> clazz = attributes[attributeStrat].getType();
			if(clazz.equals(String.class)) {
				psm.setString(index++, value.toString());
			} else if (clazz.equals(Double.class)) {
				psm.setDouble(index++,Double.valueOf(value.toString()));
			} else if(clazz.equals(int.class)) {
				psm.setInt(index++,Integer.valueOf(value.toString()));
			} else if(clazz.equals(java.util.Date.class)) {
				psm.setDate(index++, DateUtils.date2SqlDate((java.util.Date)value));
			}
		}
		
	}
	
	/**
	 * 将给定的list值一一对应的设置到PreparedStatement的参数中
	 * @param psm PreparedStatement对象
	 * @param obj 对象的基类
	 * @param attributeStrat 对象的属性从哪里开始与列名一一对应
	 * @param attributeEnd 对象的属性从哪里结束对应
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws SQLException
	 */
	protected void prepareStatementSetValue(PreparedStatement psm,List<Object> args) throws Exception {
		if (args != null) {
			int index = 1;
			for (int i=0;i<args.size();i++) {
				Object value = args.get(i);
				if(value == null) {
					psm.setObject(index++, null);
					continue;
				}
				Class<?> clazz = value.getClass();
				if(clazz.equals(String.class)) {
					psm.setString(index++, value.toString());
				} else if (clazz.equals(Double.class)) {
					psm.setDouble(index++,Double.valueOf(value.toString()));
				} else if(clazz.equals(int.class)) {
					psm.setInt(index++,Integer.valueOf(value.toString()));
				}   else if(clazz.equals(Integer.class)) {
					psm.setInt(index++,Integer.valueOf(value.toString()));
				}  else if(clazz.equals(java.util.Date.class)) {
					psm.setDate(index++, DateUtils.date2SqlDate((java.util.Date)value));
				}
			}
		}
	}

	
	/**
	 * 根据给定的id，修改数据。
	 * @param sql 修改的sql语句
	 * @param obj 对象的基类
	 * @param columnName 根据该数组一一对应对象的属性
	 * @param id 修改所依赖的id
	 * @return 返回影响行数
	 * @throws Exception
	 */
	protected int updateById(String sql,Object obj,String[] columnName,String id) throws DaoException {
		return updateById(sql, obj, columnName, id, null);
	}
	
	/**
	 * 根据给定的id（该对象含有一个外键id），修改数据。
	@param sql 修改的sql语句
	 * @param obj 对象的基类
	 * @param columnName 根据该数组一一对应对象的属性
	 * @param id 修改所依赖的id
	 * @param fkId 该对象的外键id
	 * @return 返回影响行数
	 * @throws Exception
	 */
	protected int updateById(String sql,Object obj,String[] columnName,String id,String fkId) throws DaoException {
		StringBuilder sb = new StringBuilder(sql);
		DBPoolConnection dBP = DBPoolConnection.getInstance();
		Connection cn = null;
		PreparedStatement psm = null;
		for (int i=1;i<columnName.length;i++) {
			sb.append(" " + columnName[i] + " = ?");
			if(i != columnName.length-1) {
				sb.append(",");
			}
		}
		sb.append(" WHERE id = ?");	
		try {
			cn = dBP.getConnection();
			psm = cn.prepareStatement(sb.toString());
			if(fkId == null) {
				prepareStatementSetValue(psm, obj, 1, columnName.length-1);
			} else {
				prepareStatementSetValue(psm, obj, 1, columnName.length-2);
				psm.setString(columnName.length-1,fkId);			
			}
			psm.setString(columnName.length, id);
			return psm.executeUpdate();	
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("[BaseDaoImpl:updateById:数据库错误]" + e.getMessage());
			throw new DaoException(SQL_ERORR + e.getMessage());
		} finally {
			if(cn != null) {
				try {
					cn.close();
				} catch (SQLException e) {
					logger.error("[BaseDaoImpl:updateById:数据库连接关闭错误]" + e.getMessage());
					throw new DaoException(SQL_ERORR + e.getMessage());
				}
			}	
		}
	}
	
	/**
	 * 根据给定的id，修改给定数据。
	 * @param sql 修改的sql语句
	 * @param id 修改所依赖的id
	 * @param args 要修改的数据
	 * @return 返回影响行数
	 * @throws Exception
	 */
	protected int updateById(String sql,String id,String args) throws DaoException {
		DBPoolConnection dBP = DBPoolConnection.getInstance();
		Connection cn = null;
		PreparedStatement psm = null;
		try {
			cn = dBP.getConnection();
			psm = cn.prepareStatement(sql);
			psm.setString(1, args);
			psm.setString(2, id);
			return psm.executeUpdate();	
		} catch (Exception e) {
			logger.error("[BaseDaoImpl:updateById:数据库错误]" + e.getMessage());
			throw new DaoException(SQL_ERORR + e.getMessage());
		} finally {
			if(cn != null) {
				try {
					cn.close();
				} catch (SQLException e) {
					logger.error("[BaseDaoImpl:updateById:数据库连接关闭错误]" + e.getMessage());
					throw new DaoException(SQL_ERORR + e.getMessage());
				}
			}	
		}
	}
	

	/**
	 * 查询未使用的最小id
	 * @param sql 查询未使用的最小id语句
	 * @param sql2 当已有的id连续时，查询最大的id数
	 * @param columnName 根据该数组一一对应对象的属性
	 * @return 返回查询到的符合要求的id字符串
	 * @throws Exception
	 */
	protected String queryMinEmptyId(String sql,String sql2,String columnName) throws DaoException {
		DBPoolConnection dBP = DBPoolConnection.getInstance();
		Connection cn = null;
		PreparedStatement psm = null;
		ResultSet result = null;
		try {
			cn = dBP.getConnection();
			psm = cn.prepareStatement(sql);
			result = psm.executeQuery();
			if(result.next()) {
				return result.getString(columnName);
				
			}
			return queryMaxId(cn,sql2,columnName);		
		} catch (Exception e) {
			logger.error("[BaseDaoImpl:queryMinEmptyId:数据库错误]" + e.getMessage());
			throw new DaoException(SQL_ERORR + e.getMessage());
		} finally {
			if(cn != null) {
				try {
					cn.close();
				} catch (SQLException e) {
					logger.error("[BaseDaoImpl:queryMinEmptyId:数据库连接关闭错误]" + e.getMessage());
					throw new DaoException(SQL_ERORR + e.getMessage());
				}
			}	
		}
	}
	
	/**
	 * 当已有的id连续时，查询最大的id数
	 * @param cn 查询未使用的最小id启用的数据库连接
	 * @param sql 当已有的id连续时，查询最大的id数sql语句
	 * @param columnName 根据该数组一一对应对象的属性
	 * @return 返回查询到的符合要求的id字符串
	 * @throws Exception
	 */
	protected String queryMaxId(Connection cn,String sql,String columnName) throws DaoException {
		PreparedStatement psm = null;
		ResultSet result = null;
		try {
			psm = cn.prepareStatement(sql);
			result = psm.executeQuery();
			if(result.next()) {
				return result.getString(columnName);
			}
			return null;		
		} catch (Exception e) {
			logger.error("[BaseDaoImpl:queryMaxId:数据库错误]" + e.getMessage());
			throw new DaoException(SQL_ERORR + e.getMessage());
		} finally {
			if(cn != null) {
				try {
					cn.close();
				} catch (SQLException e) {
					logger.error("[BaseDaoImpl:queryMaxId:数据库连接关闭错误]" + e.getMessage());
					throw new DaoException(SQL_ERORR + e.getMessage());
				}
			}	
		}
	}
	
	
	/**
	 * 根据给定的id删除数据
	 * @param sql 删除的sql语句
	 * @param id 删除的数据的id
	 * @return 返回影响行数
	 * @throws Exception
	 */
	protected int deleteById(String sql,String id) throws DaoException {
		List<String> idArr = new ArrayList<>();  
		idArr.add(id);
		return deleteById(sql,idArr);
	}
	
	/**
	 * 根据给定的id删除数据
	 * @param sql 删除的sql语句
	 * @param idArr 删除的数据的id数组
	 * @return 返回影响行数
	 * @throws Exception
	 */
	protected int deleteById(String sql,List<String> idArr) throws DaoException {
		DBPoolConnection dBP = DBPoolConnection.getInstance();
		Connection cn = null;
		PreparedStatement psm = null;
		try {
			cn = dBP.getConnection();
			cn.setAutoCommit(false);
			for(int i=0;i<idArr.size();i++) {
				psm = cn.prepareStatement(sql);
				psm.setString(1, idArr.get(i));
				psm.executeUpdate();	
				if(i == 1000) {
					cn.commit();
				}
			}
			cn.commit();
			return 1;
		} catch (Exception e) {
			logger.error("[BaseDaoImpl:deleteById:数据库错误]" + e.getMessage());
			throw new DaoException(SQL_ERORR + e.getMessage());
		} finally {
			if(cn != null) {
				try {
					cn.close();
				} catch (SQLException e) {
					logger.error("[BaseDaoImpl:deleteById:数据库连接关闭错误]" + e.getMessage());
					throw new DaoException(SQL_ERORR + e.getMessage());
				}
			}	
		}
	}
	
	
	/**
	 * 给定id集合，批量添加给定字段的值
	 * @param sql
	 * @param idArr id集合
	 * @param arg 字段的值
	 * @return
	 */
	protected int updateById(String sql, List<String> idArr, String arg) throws DaoException  {
		DBPoolConnection dBP = DBPoolConnection.getInstance();
		Connection cn = null;
		PreparedStatement psm = null;
		try {
			cn = dBP.getConnection();
			cn.setAutoCommit(false);
			for(int i=0;i<idArr.size();i++) {
				psm = cn.prepareStatement(sql);
				psm.setString(1, arg);
				psm.setString(2, idArr.get(i));
				psm.executeUpdate();	
				if(i == 1000) {
					cn.commit();
				}
			}
			cn.commit();
			return 1;
		} catch (Exception e) {
			logger.error("[BaseDaoImpl:deleteById:数据库错误]" + e.getMessage());
			throw new DaoException(SQL_ERORR + e.getMessage());
		} finally {
			if(cn != null) {
				try {
					cn.close();
				} catch (SQLException e) {
					logger.error("[BaseDaoImpl:updateById:数据库连接关闭错误]" + e.getMessage());
					throw new DaoException(SQL_ERORR + e.getMessage());
				}
			}	
		}
	}


	protected int getCount(String sql, String name) throws DaoException {
		return getCount(sql, name,null);
	}
	
	/**
	 * 获取全部数据量
	 * @param sql 
	 * @param name count(1)的别名
	 * @param args 需要设置的参数
	 * @return int
	 * @throws DaoException
	 */
	protected int getCount(String sql, String name, List<Object> args) throws DaoException {
		DBPoolConnection dBP = DBPoolConnection.getInstance();
		Connection cn = null;
		PreparedStatement psm = null;
		ResultSet result = null;
		try {
			cn = dBP.getConnection();
			psm = cn.prepareStatement(sql);
			prepareStatementSetValue(psm, args);
			result = psm.executeQuery();
			if(result.next()) {
				return result.getInt(name);
			}
			return 0;		
		} catch (Exception e) {
			logger.error("[BaseDaoImpl:getCount:数据库错误]" + e.getMessage());
			throw new DaoException(SQL_ERORR + e.getMessage());
		} finally {
			if(cn != null) {
				try {
					cn.close();
				} catch (SQLException e) {
					logger.error("[BaseDaoImpl:getCount:数据库连接关闭错误]" + e.getMessage());
					throw new DaoException(SQL_ERORR + e.getMessage());
				}
			}	
		}
	}




	
}
