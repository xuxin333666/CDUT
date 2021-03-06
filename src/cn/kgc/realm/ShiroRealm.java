package cn.kgc.realm;


import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.kgc.dao.impl.UserDaoImpl;
import cn.kgc.dao.intf.UserDao;
import cn.kgc.exception.DaoException;
import cn.kgc.exception.ServiceException;
import cn.kgc.model.User;
import cn.kgc.service.impl.PermsServiceImpl;
import cn.kgc.service.intf.PermsService;

public class ShiroRealm extends AuthorizingRealm {
	private static final Logger logger = LoggerFactory.getLogger(ShiroRealm.class); 
	@Override//认证方法
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken uToken = (UsernamePasswordToken)token;
		//获取当前的登录用户
		String username = uToken.getUsername();
		
		//只需要从数据库中查出该用户对应的密码
		UserDao userDao = new UserDaoImpl();
		User user;
		try {
			user = userDao.query(username);
			
			ByteSource salt = ByteSource.Util.bytes(username);
			//第一个参数:查询出来的用户对象
			//第二个参数:查询出来的密码
			//第三个参数:给密码添加的盐值;
			//第四个参数:this.getName();
			
			SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(),salt ,this.getName());
			
			return simpleAuthenticationInfo;
		} catch (UnknownAccountException e) {
			logger.error("[ShiroRealm:AuthenticationInfo:找不到用户]" + e.getMessage());
			throw new UnknownAccountException();
		} catch (DaoException e) {
			logger.error("[ShiroRealm:AuthenticationInfo:数据库错误]" + e.getMessage());
			throw new AuthenticationException(e.getMessage());
		}
		
	}

	@Override//授权方法
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection collection) {
		User user = (User)collection.getPrimaryPrincipal();
		System.out.println("【当前登录用户是---------->】" + user.getUsername());
		
		PermsService permsService = new PermsServiceImpl();
		
		Set<String> perms;
		try {
			perms = permsService.getPerms(user);
		} catch (ServiceException e) {
			logger.error("[ShiroRealm:doGetAuthorizationInfo]" + e.getMessage());
			throw new AuthenticationException(e.getMessage());
		}
		if("admin".equals(user.getUsername())) {
			
			perms.add("group:add");
			perms.add("group:modify");
			perms.add("group:del");
			perms.add("group:save");
			perms.add("group:enable");
			perms.add("group:disable");
			
			perms.add("student:add");
			perms.add("student:modify");
			perms.add("student:del");
			perms.add("student:save");
			perms.add("student:proFileUpload");
			perms.add("student:fileUpload");
		}else {
		}
		
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		simpleAuthorizationInfo.setStringPermissions(perms);
		
		return simpleAuthorizationInfo;
	}
	
	public static void main(String[] args) {
	    String hashAlgorithmName = "MD5";//加密方式
	    Object crdentials = "123";//密码原值
	    Object salt = "123";//盐值
	    int hashIterations = 1024;//加密1024次
	    Object result = new SimpleHash(hashAlgorithmName,crdentials,salt,hashIterations);
	    System.out.println(result);
	}


}
