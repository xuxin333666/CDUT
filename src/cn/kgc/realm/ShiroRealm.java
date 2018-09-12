package cn.kgc.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.kgc.dao.impl.UserDaoImpl;
import cn.kgc.dao.intf.UserDao;
import cn.kgc.exception.DaoException;
import cn.kgc.model.User;

public class ShiroRealm extends AuthorizingRealm {
	private static final Logger logger = LoggerFactory.getLogger(ShiroRealm.class); 
	@Override//��֤����
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken uToken = (UsernamePasswordToken)token;
		//��ȡ��ǰ�ĵ�¼�û�
		String username = uToken.getUsername();
		
		//ֻ��Ҫ�����ݿ��в�����û���Ӧ������
		UserDao userDao = new UserDaoImpl();
		User user;
		try {
			
			user = userDao.query(username);
			
			ByteSource salt = ByteSource.Util.bytes(username);
			//��һ������:��ѯ�������û�����
			//�ڶ�������:��ѯ����������
			//����������:��������ӵ���ֵ;
			//���ĸ�����:this.getName();
			
			SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(),salt ,this.getName());
			
			return simpleAuthenticationInfo;
		} catch (UnknownAccountException e) {
			logger.error("[ShiroRealm:AuthenticationInfo:�Ҳ����û�]" + e.getMessage());
			throw new UnknownAccountException();
		} catch (DaoException e) {
			logger.error("[ShiroRealm:AuthenticationInfo:���ݿ����]" + e.getMessage());
			throw new AuthenticationException(e.getMessage());
		}
		
	}

	@Override//��Ȩ����
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection collection) {
		System.out.println("��Ȩ����ִ��");
		return null;
	}
	
	public static void main(String[] args) {
	    String hashAlgorithmName = "MD5";//���ܷ�ʽ
	    Object crdentials = "123";//����ԭֵ
	    Object salt = "admin";//��ֵ
	    int hashIterations = 1024;//����1024��
	    Object result = new SimpleHash(hashAlgorithmName,crdentials,salt,hashIterations);
	    System.out.println(result);
	}


}
