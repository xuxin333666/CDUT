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
	
	protected final String SQL_ERORR = "��̨���ݴ���";
	
	protected static final Logger logger = LoggerFactory.getLogger(BaseDaoImpl.class); 

	/**
	 * ����sql����ѯ���ݣ����������ɶ��󣬷���list����
	 * @param sql sql���
	 * @param clazz Ҫ���ɵĶ����class��
	 * @param subClazz Ҫ���ɵĶ�����Ӷ����class��
	 * @param columnName ���ݸ�����һһ��Ӧ���������
	 * @return ����List<Object>
	 * @throws Exception
	 */
	protected List<T> query(String sql,Class<?> clazz,Class<?> subClazz,String[] columnName) throws DaoException {
		return queryByConditions(sql, clazz, subClazz, columnName, null,0,0);
	}
	

	/**
	 * ����sql����ѯ���ݣ����������ɶ��󣬷���list����
	 * @param sql sql���
	 * @param clazz Ҫ���ɵĶ����class��
	 * @param subClazz Ҫ���ɵĶ�����Ӷ����class��
	 * @param columnName ���ݸ�����һһ��Ӧ���������
	 * @param pageBeanDto ��ҳ����
	 * @return ����List<Object>
	 * @throws Exception
	 */
	public List<T> query(String sql, Class<?> clazz, Class<?> subClazz, String[] columnName,
			PageBean<?> pageBeanDto) throws DaoException {
		return queryByConditions(sql, clazz, subClazz, columnName, null,pageBeanDto.getCurrentPage(),pageBeanDto.getCountPerPage());
	}
	
	/**
	 * ���ݸ��������������ѯ���ݣ����������ɶ��󣬷���list����
	 * @param sql sql���
	 * @param clazz Ҫ���ɵĶ����class��
	 * @param subClazz Ҫ���ɵĶ�����Ӷ����class��
	 * @param columnName ���ݸ�����һһ��Ӧ���������
	 * @param args ���ݸ��������ò�ѯ����
	 * @return ����List<Object>
	 * @throws Exception
	 */
	protected List<T> queryByConditions(String sql,Class<?> clazz,Class<?> subClazz,String[] columnName,List<Object> args) throws DaoException {
		return queryByConditions(sql, clazz, subClazz, columnName, args,0,0);
	}
	
	
	/**
	 * ����sql����ѯ����,����ѯ�����ķ�ҳ��ѯ�����������ɶ��󣬷���list����
	 * @param sql sql���
	 * @param clazz Ҫ���ɵĶ����class��
	 * @param subClazz Ҫ���ɵĶ�����Ӷ����class��
	 * @param columnName ���ݸ�����һһ��Ӧ���������
	 * @param arg ��ѯ����
	 * @param pageBeanDto ��ҳ����
	 * @return ����List<Object>
	 * @throws Exception
	 */
	public List<T> query(String sql, Class<?> clazz, Class<?> subClazz, String[] columnName,
			String arg, PageBean<?> pageBeanDto) throws DaoException {
		List<Object> args = new ArrayList<>();
		args.add(arg);
		return queryByConditions(sql, clazz, subClazz, columnName, args,pageBeanDto.getCurrentPage(),pageBeanDto.getCountPerPage());
	}
	
	
	/**
	 * ���ݸ��������������ѯ���ݣ����������ɶ��󣬷���list����
	 * @param sql sql���
	 * @param clazz Ҫ���ɵĶ����class��
	 * @param subClazz Ҫ���ɵĶ�����Ӷ����class��
	 * @param columnName ���ݸ�����һһ��Ӧ���������
	 * @param args ���ݸ�list���ò�ѯ����
	 * @return ����List<Object>
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
			logger.error("[BaseDaoImpl:queryByConditions:���ݿ��쳣]" + e.getMessage());
			throw new DaoException(SQL_ERORR + e.getMessage());
		} finally {
			if(cn != null) {
				try {
					cn.close();
				} catch (SQLException e) {
					logger.error("[BaseDaoImpl:queryByConditions:���ݿ����ӹرմ���]" + e.getMessage());
					throw new DaoException(SQL_ERORR + e.getMessage());
				}
			}
		}
		return list;
	}
	
	/**
	 * ���ݸ����Ĳ�ѯ���ݣ����������ɶ��󣬷���list����
	 * @param sql sql���
	 * @param clazz Ҫ���ɵĶ����class��
	 * @param subClazz Ҫ���ɵĶ�����Ӷ����class��
	 * @param columnName ���ݸ�����һһ��Ӧ���������
	 * @return ����List<Object>
	 * @throws Exception
	 */
	protected List<T> queryById(String sql,Class<?> clazz,Class<?> subClazz,String[] columnName,String id) throws DaoException {	
		List<Object> args = new ArrayList<>();
		args.add(id);
		return queryByConditions(sql, clazz, subClazz, columnName, args,0,0);
	}
	
	/**
	 * ����sql����ѯ�Ƿ���ڸ�����id
	 * @param sql sql���
	 *  @param id id
	 * @return �����Ƿ����
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
			logger.error("[BaseDaoImpl:queryIdExist:���ݿ��쳣]" + e.getMessage());
			throw new DaoException(SQL_ERORR + e.getMessage());
		} finally {
			if(cn != null) {
				try {
					cn.close();
				} catch (SQLException e) {
					logger.error("[BaseDaoImpl:queryIdExist:���ݿ����ӹرմ���]" + e.getMessage());
					throw new DaoException(SQL_ERORR + e.getMessage());
				}
			}
		}
	}
	
	
	/**
	 * ��������������ɶ��󣬷���list
	 * @param result ��ѯ�Ľ����
	 * @param list Ҫ���������
	 * @param clazz Ҫ���ɵĶ����class��
	 * @param subClazz Ҫ���ɵĶ�����Ӷ����class��
	 * @param columnName ���ݸ�����һһ��Ӧ���������
	 * @throws Exception
	 */
	protected void result2List(ResultSet result, List<T> list,Class<?> clazz,Class<?> subClazz,String[] columnName) throws DaoException {
		try {
			while(result.next()) {
				T obj = setValue(result,clazz,subClazz,columnName,0);
				list.add(obj);
			}
		} catch (Exception e) {
			logger.error("[BaseDaoImpl:result2List:���ݿ��쳣]" + e.getMessage());
			throw new DaoException(SQL_ERORR + e.getMessage());
		}
	}
	
	/**
	 * �����������������һһ��Ӧ���ӽ�����ó����ݸ�ֵ�������Ӧ��������
	 * @param result ��ѯ�Ľ����
	 * @param clazz Ҫ���ɵĶ����class��
	 * @param subClazz Ҫ���ɵĶ�����Ӷ����class��
	 * @param columnName ���ݸ�����һһ��Ӧ���������
	 * @param index �Ѿ��������ڼ���������
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private T setValue(ResultSet result,Class<?> clazz,Class<?> subClazz,String[] columnName,int index) throws DaoException  {
		T obj;
		try {
			obj = (T)clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			logger.error("[BaseDaoImpl:setValue:���󴴽�ʵ��ʧ��]" + e.getMessage());
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
			logger.error("[BaseDaoImpl:setValue:���ݿ��ȡ���ݲ�������]" + e.getMessage());
			throw new DaoException(SQL_ERORR + e.getMessage());
		} catch (IllegalArgumentException | IllegalAccessException e) {
			logger.error("[BaseDaoImpl:setValue:�������ö�������ֵʧ��]" + e.getMessage());
			throw new DaoException(SQL_ERORR + e.getMessage());
		}
		return obj;
	}
	
	/**
	 * �������Ķ�����ӵ����ݿ�
	 * @param sql ��ӵ�sql���
	 * @param obj ����Ļ���
	 * @param attributeStrat ��������Դ����￪ʼ������һһ��Ӧ
	 * @param attributeEnd ��������Դ����������Ӧ
	 * @return ����Ӱ������
	 * @throws Exception
	 */
	protected int insert(String sql,Object obj,int attributeStrat,int attributeEnd) throws DaoException {
		return insert(sql, obj, attributeStrat, attributeEnd, new String[]{null});
	}
	
	/**
	 * �������Ķ�����һ�����id������ӵ����ݿ�
	 * @param sql ��ӵ�sql���
	 * @param obj ����Ļ���
	 * @param attributeStrat ��������Դ����￪ʼ������һһ��Ӧ
	 * @param attributeEnd ��������Դ����������Ӧ
	 * @param fkId ���id��ֵ
	 * @return ����Ӱ������
	 * @throws Exception
	 */
	protected int insert(String sql,Object obj,int attributeStrat,int attributeEnd, String fkId) throws DaoException {
		return insert(sql, obj, attributeStrat, attributeEnd, new String[]{fkId});
	}
	
	
	/**
	 *  �������Ķ����ж�����id������ӵ����ݿ�
	 * @param sql ��ӵ�sql���
	 * @param obj ����Ļ���
	 * @param attributeStrat ��������Դ����￪ʼ������һһ��Ӧ
	 * @param attributeEnd ��������Դ����������Ӧ
	 * @param fkIds ���id��ֵ������
	 * @return ����Ӱ������
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
			logger.error("[BaseDaoImpl:insert:���ݿ����]" + e.getMessage());
			throw new DaoException(SQL_ERORR + e.getMessage());
		} finally {
			if(cn != null) {
				try {
					cn.close();
				} catch (SQLException e) {
					logger.error("[BaseDaoImpl:insert:���ݿ����ӹرմ���]" + e.getMessage());
					throw new DaoException(SQL_ERORR + e.getMessage());
				}
			}	
		}
	}
	
	
	/**
	 * �����������һһ��Ӧ�����õ�PreparedStatement�Ĳ����У�Ҫ������˳��һ��
	 * @param psm PreparedStatement����
	 * @param obj ����Ļ���
	 * @param attributeStrat ��������Դ����￪ʼ������һһ��Ӧ
	 * @param attributeEnd ��������Դ����������Ӧ
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
	 * ��������listֵһһ��Ӧ�����õ�PreparedStatement�Ĳ�����
	 * @param psm PreparedStatement����
	 * @param obj ����Ļ���
	 * @param attributeStrat ��������Դ����￪ʼ������һһ��Ӧ
	 * @param attributeEnd ��������Դ����������Ӧ
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
	 * ���ݸ�����id���޸����ݡ�
	 * @param sql �޸ĵ�sql���
	 * @param obj ����Ļ���
	 * @param columnName ���ݸ�����һһ��Ӧ���������
	 * @param id �޸���������id
	 * @return ����Ӱ������
	 * @throws Exception
	 */
	protected int updateById(String sql,Object obj,String[] columnName,String id) throws DaoException {
		return updateById(sql, obj, columnName, id, null);
	}
	
	/**
	 * ���ݸ�����id���ö�����һ�����id�����޸����ݡ�
	@param sql �޸ĵ�sql���
	 * @param obj ����Ļ���
	 * @param columnName ���ݸ�����һһ��Ӧ���������
	 * @param id �޸���������id
	 * @param fkId �ö�������id
	 * @return ����Ӱ������
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
			logger.error("[BaseDaoImpl:updateById:���ݿ����]" + e.getMessage());
			throw new DaoException(SQL_ERORR + e.getMessage());
		} finally {
			if(cn != null) {
				try {
					cn.close();
				} catch (SQLException e) {
					logger.error("[BaseDaoImpl:updateById:���ݿ����ӹرմ���]" + e.getMessage());
					throw new DaoException(SQL_ERORR + e.getMessage());
				}
			}	
		}
	}
	
	/**
	 * ���ݸ�����id���޸ĸ������ݡ�
	 * @param sql �޸ĵ�sql���
	 * @param id �޸���������id
	 * @param args Ҫ�޸ĵ�����
	 * @return ����Ӱ������
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
			logger.error("[BaseDaoImpl:updateById:���ݿ����]" + e.getMessage());
			throw new DaoException(SQL_ERORR + e.getMessage());
		} finally {
			if(cn != null) {
				try {
					cn.close();
				} catch (SQLException e) {
					logger.error("[BaseDaoImpl:updateById:���ݿ����ӹرմ���]" + e.getMessage());
					throw new DaoException(SQL_ERORR + e.getMessage());
				}
			}	
		}
	}
	

	/**
	 * ��ѯδʹ�õ���Сid
	 * @param sql ��ѯδʹ�õ���Сid���
	 * @param sql2 �����е�id����ʱ����ѯ����id��
	 * @param columnName ���ݸ�����һһ��Ӧ���������
	 * @return ���ز�ѯ���ķ���Ҫ���id�ַ���
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
			logger.error("[BaseDaoImpl:queryMinEmptyId:���ݿ����]" + e.getMessage());
			throw new DaoException(SQL_ERORR + e.getMessage());
		} finally {
			if(cn != null) {
				try {
					cn.close();
				} catch (SQLException e) {
					logger.error("[BaseDaoImpl:queryMinEmptyId:���ݿ����ӹرմ���]" + e.getMessage());
					throw new DaoException(SQL_ERORR + e.getMessage());
				}
			}	
		}
	}
	
	/**
	 * �����е�id����ʱ����ѯ����id��
	 * @param cn ��ѯδʹ�õ���Сid���õ����ݿ�����
	 * @param sql �����е�id����ʱ����ѯ����id��sql���
	 * @param columnName ���ݸ�����һһ��Ӧ���������
	 * @return ���ز�ѯ���ķ���Ҫ���id�ַ���
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
			logger.error("[BaseDaoImpl:queryMaxId:���ݿ����]" + e.getMessage());
			throw new DaoException(SQL_ERORR + e.getMessage());
		} finally {
			if(cn != null) {
				try {
					cn.close();
				} catch (SQLException e) {
					logger.error("[BaseDaoImpl:queryMaxId:���ݿ����ӹرմ���]" + e.getMessage());
					throw new DaoException(SQL_ERORR + e.getMessage());
				}
			}	
		}
	}
	
	
	/**
	 * ���ݸ�����idɾ������
	 * @param sql ɾ����sql���
	 * @param id ɾ�������ݵ�id
	 * @return ����Ӱ������
	 * @throws Exception
	 */
	protected int deleteById(String sql,String id) throws DaoException {
		List<String> idArr = new ArrayList<>();  
		idArr.add(id);
		return deleteById(sql,idArr);
	}
	
	/**
	 * ���ݸ�����idɾ������
	 * @param sql ɾ����sql���
	 * @param idArr ɾ�������ݵ�id����
	 * @return ����Ӱ������
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
			logger.error("[BaseDaoImpl:deleteById:���ݿ����]" + e.getMessage());
			throw new DaoException(SQL_ERORR + e.getMessage());
		} finally {
			if(cn != null) {
				try {
					cn.close();
				} catch (SQLException e) {
					logger.error("[BaseDaoImpl:deleteById:���ݿ����ӹرմ���]" + e.getMessage());
					throw new DaoException(SQL_ERORR + e.getMessage());
				}
			}	
		}
	}
	
	
	/**
	 * ����id���ϣ�������Ӹ����ֶε�ֵ
	 * @param sql
	 * @param idArr id����
	 * @param arg �ֶε�ֵ
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
			logger.error("[BaseDaoImpl:deleteById:���ݿ����]" + e.getMessage());
			throw new DaoException(SQL_ERORR + e.getMessage());
		} finally {
			if(cn != null) {
				try {
					cn.close();
				} catch (SQLException e) {
					logger.error("[BaseDaoImpl:updateById:���ݿ����ӹرմ���]" + e.getMessage());
					throw new DaoException(SQL_ERORR + e.getMessage());
				}
			}	
		}
	}


	protected int getCount(String sql, String name) throws DaoException {
		return getCount(sql, name,null);
	}
	
	/**
	 * ��ȡȫ��������
	 * @param sql 
	 * @param name count(1)�ı���
	 * @param args ��Ҫ���õĲ���
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
			logger.error("[BaseDaoImpl:getCount:���ݿ����]" + e.getMessage());
			throw new DaoException(SQL_ERORR + e.getMessage());
		} finally {
			if(cn != null) {
				try {
					cn.close();
				} catch (SQLException e) {
					logger.error("[BaseDaoImpl:getCount:���ݿ����ӹرմ���]" + e.getMessage());
					throw new DaoException(SQL_ERORR + e.getMessage());
				}
			}	
		}
	}




	
}
