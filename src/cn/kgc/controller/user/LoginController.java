package cn.kgc.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
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

@WebServlet("/login")
public class LoginController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class); 
	private static final String SESSION__USER_COUNT = "userCount";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
			logger.error("[LoginController:doPost]" + msg);
			req.getRequestDispatcher("index.jsp").forward(req, resp);
		}else{
			session.setAttribute(SESSION__USER_COUNT, new UserCountListener());
			if("123".equals(password)) {
				req.setAttribute("username", username);
				req.getRequestDispatcher("moidfypwd.jsp").forward(req, resp);
			} else {
				resp.sendRedirect("admin/main?command=index");
			}
		}
	}
	
}
