package cn.kgc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import cn.kgc.Listener.UserCountListener;
import cn.kgc.service.impl.UserServiceImpl;
import cn.kgc.service.intf.UserService;


public class UserController extends CoreController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class); 
	private static final String SESSION__USER_COUNT = "userCount";
	UserService userService = new UserServiceImpl();

	public UserController() {
		super.setController(this);
	}
	public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		HttpSession session = req.getSession();
		
		Subject subject = SecurityUtils.getSubject();
		
		UsernamePasswordToken token = new UsernamePasswordToken(username,password);
		String msg = null;
		try {
			//登录，即身份验证  
			subject.login(token);
		} catch (UnknownAccountException e) {
			msg = "用户名不存在！";
		}catch (IncorrectCredentialsException e) {
			msg = "密码不正确！";
		}catch (AuthenticationException e) {
			msg = "其他异常："+e.getMessage();
		}
		
		if(msg!=null){
			req.setAttribute("msg", msg);
			logger.error("[UserController:login]" + msg);
			resp.getWriter().println(msg);
		}else{
			session.setAttribute(SESSION__USER_COUNT, new UserCountListener());
			if("1234".equals(password)) {
				req.setAttribute("username", username);
				req.getRequestDispatcher("moidfypwd.jsp").forward(req, resp);
			} else {
				resp.getWriter().print(true);
			}
		}
	}
	
	public void modifyUserPwd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		try {
			userService.modifyPwd(username,password);
			req.getRequestDispatcher("main").forward(req, resp);
		} catch (Exception e) {
			logger.error("[UserController:modifyUserPwd]" + e.getMessage());
			req.getRequestDispatcher("/error.jsp").forward(req, resp);
		}
	}
	
	
	public void getUserOnLineCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String count = UserCountListener.getUserCount().toString();
		resp.getWriter().print(count);
	}
}
