package cn.kgc.dao.impl;


import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.kgc.exception.DaoException;
import cn.kgc.utils.DBPoolConnection;
import cn.kgc.utils.DateUtils;

public class BaseDao2<T> {
	protected static final Logger logger = LoggerFactory.getLogger(BaseDao2.class); 
	
	
	/**
	 * ���ݸ����Ķ����Լ���Ҫ�޸ĵ����������ݿ����֣���һһ��Ӧ���¡��޷������޸�
	 * @param sql
	 * @param t ���󣬸��ݴ���ֵ
	 * @param columnNames ������id�������жϷ������,��֧��һ�������ж�
	 * @return
	 * @throws DaoException
	 */
	protected int update(String sql,T t,String[] columnNames) throws DaoException {
		List<Object> args = colName2args(t,columnNames);
		return update(sql,args,columnNames);

	}  
	
	/**
	 * ���ݸ����Ĳ��������Լ���Ҫ�޸ĵ����������ݿ����֣���һһ��Ӧ���¡��޷������޸�
	 * @param sql
	 * @param args ��������
	 * @param columnNames ������id�������жϷ������,��֧��һ�������ж�
	 * @return
	 * @throws DaoException
	 */
	protected int update(String sql,List<Object> args,String[] columnNames) throws DaoException {
		StringBuilder sb = new StringBuilder(sql);
		for (int i=0;i<columnNames.length;i++) {
			if(i == columnNames.length-1) {
				sb.append(" WHERE ");	
			}
			sb.append(" " + columnNames[i] + " = ?");
			if(i < columnNames.length-2) {
				sb.append(",");
			}
		}
		return simpleUpdate(sb.toString(),args);
	}
	
	/**
	 * �����Ѿ�ƴ�պõ�sql����һһ��Ӧ�Ĳ������ϣ���������
	 * @param sql
	 * @param args
	 * @return
	 * @throws DaoException
	 */
	protected int simpleUpdate(String sql,List<Object> args) throws DaoException {
		DBPoolConnection dBP = DBPoolConnection.getInstance();
		Connection cn = null;
		PreparedStatement psm = null;
		try {
			cn = dBP.getConnection();
			psm = cn.prepareStatement(sql);
			prepareStatementSetValue(psm, args);
			return psm.executeUpdate();	
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("[BaseDao2:simpleUpdate:���ݿ����]" + e.getMessage());
			throw new DaoException(e.getMessage());
		} finally {
			if(cn != null) {
				try {
					cn.close();
				} catch (SQLException e) {
					logger.error("[BaseDao2:simpleUpdate:���ݿ����ӹرմ���]" + e.getMessage());
					throw new DaoException(e.getMessage());
				}
			}	
		}
	}
	
	/**
	 * �����޸����ݣ��ж�����Ϊid��
	 * @param sql
	 * @param idArr
	 * @param args
	 * @param colNames
	 * @return
	 * @throws DaoException
	 */
	protected int updatesById(String sql,List<String> idArr, List<Object> args,List<String> colNames) throws DaoException {
		StringBuilder sb = new StringBuilder(sql);
		DBPoolConnection dBP = DBPoolConnection.getInstance();
		Connection cn = null;
		PreparedStatement psm = null;
		for (int i=0;i<colNames.size();i++) {
			sb.append(" " + colNames.get(i) + " = ?");
			if(i < colNames.size()-1) {
				sb.append(",");
			}
		}
		sb.append(" WHERE id = ?");
		try {
			cn = dBP.getConnection();
			cn.setAutoCommit(false);
			for(int i=0;i<idArr.size();i++) {
				psm = cn.prepareStatement(sb.toString());
				prepareStatementSetValue(psm, args);
				psm.setString(args.size() + 1, idArr.get(i));
				psm.executeUpdate();	
				if(i == 1000) {
					cn.commit();
				}
			}
			cn.commit();
			return 1;
		} catch (Exception e) {
			logger.error("[BaseDao2:updatesById:���ݿ����]" + e.getMessage());
			throw new DaoException(e.getMessage());
		} finally {
			if(cn != null) {
				try {
					cn.close();
				} catch (SQLException e) {
					logger.error("[BaseDao2:updatesById:���ݿ����ӹرմ���]" + e.getMessage());
					throw new DaoException(e.getMessage());
				}
			}	
		}
	}
	
	/**
	 * �����޸����ݣ��ж�����Ϊid���ʺ�ֻ�޸Ķ������ݵ�
	 * @param sql
	 * @param idArr
	 * @param argMap
	 * @return
	 * @throws DaoException
	 */
	protected int updatesById(String sql,List<String> idArr, Map<String, Object> argMap) throws DaoException {
		Set<String> keys = argMap.keySet();
		List<String> colNames = new ArrayList<>(keys);
		List<Object> args = new ArrayList<>();
		Iterator<String> it = keys.iterator();
		while(it.hasNext()) {
			Object arg = argMap.get(it.next());
			args.add(arg);
		}
		return updatesById(sql, idArr,  args, colNames);
	}  
	
	/**
	 * �����޸����ݣ��ж�����Ϊid���ʺ�ֻ�޸�һ�����ݵ�
	 * @param sql
	 * @param idArr
	 * @param key
	 * @param value
	 * @return
	 * @throws DaoException
	 */
	protected int updatesById(String sql,List<String> idArr, String key,Object value) throws DaoException {
		Map<String, Object> argMap = new HashMap<>();
		argMap.put(key, value);
		return updatesById(sql, idArr, argMap);
	}  
	
	
	
	/**
	 * ��Сд���»��ߵ����ݿ����������ݸ����Ķ���ʵ����ת���ɸö����Ӧ���Ե�ֵlist����
	 * @param t
	 * @param columnNames
	 * @return
	 * @throws DaoException
	 */
	private List<Object> colName2args(T t, String[] columnNames) throws DaoException  {
		List<Object> args = new ArrayList<>();
		Class<?> clazz = t.getClass();
		for (String colName : columnNames) {
			String fieldName = colName2fieldName(colName);
			Field field = null;
			try {
				field = clazz.getDeclaredField(fieldName);
			} catch (Exception e) {
				try {
					field = clazz.getDeclaredField(colName);
				} catch (Exception e1) {
					logger.error("[BaseDao2:colName2args:�޴�����]" + e.getMessage());
					throw new DaoException(e.getMessage());
				}
			}
			field.setAccessible(true);
			Object value = null;
			try {
				value = field.get(t);
			} catch (Exception e) {
				logger.error("[BaseDao2:colName2args:�����ȡֵʧ��]" + e.getMessage());
				throw new DaoException(e.getMessage());
			}
			args.add(value);
		}
		return args;
	}



	/**
	 * ��Сд���»��ߵ����ݿ�����ת�����շ�������javabean������
	 * @param colName
	 * @return
	 */
	private String colName2fieldName(String colName) {
		String[] arr = colName.split("_");
		if(arr.length != 1) {
			StringBuilder sb = new StringBuilder();
			sb.append(arr[0]);
			for(int i=1;i<arr.length;i++) {
				char[] charArr = arr[i].toCharArray();
				char c = charArr[0];
				if(Character.isLowerCase(c)) {
					charArr[0] =(char)((int)c - 32);
				}
				sb.append(charArr);
			}
			return sb.toString();
		}
		return colName;
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
	private void prepareStatementSetValue(PreparedStatement psm,List<Object> args) throws Exception {
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
	
	


	public List<Map<String, Object>> statisticalQuery(String sql) throws DaoException {
		List<Map<String, Object>> values = new ArrayList<>();
		DBPoolConnection dbp = new DBPoolConnection();
		Connection cn = null;
		PreparedStatement psm = null;
		ResultSet result = null;
		try {
			cn = dbp.getConnection();
			psm = cn.prepareStatement(sql);
			result = psm.executeQuery();
			ResultSetMetaData rsmd = result.getMetaData();
			int count=rsmd.getColumnCount();
			String[] name=new String[count];
			for(int i=0;i<count;i++) {
				name[i]=rsmd.getColumnName(i+1);
			}
			while(result.next()) {
				Map<String, Object> temp = new HashMap<>();
				for(int i=0;i<name.length;i++) {
					temp.put(name[i], result.getObject(name[i]));
				}
				values.add(temp);
			}
		} catch (Exception e) {
			logger.error("[BaseDao2:query:���ݿ��쳣]" + e.getMessage());
			throw new DaoException(e.getMessage());
		} finally {
			if(cn != null) {
				try {
					cn.close();
				} catch (SQLException e) {
					logger.error("[BaseDao2:query:���ݿ����ӹرմ���]" + e.getMessage());
					throw new DaoException(e.getMessage());
				}
			}
		}
		return values;
	}

}
