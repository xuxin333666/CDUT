package cn.kgc.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

import cn.kgc.Listener.UserCountListener;
import cn.kgc.model.User;
import cn.kgc.service.impl.UserServiceImpl;
import cn.kgc.service.intf.UserService;

@WebServlet("/login")
public class LoginController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class); 
	private static final String SESSION_NAME = "userInfo";
	private static final String SESSION__USER_COUNT = "userCount";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String data = req.getParameter("data");
		HttpSession session = req.getSession();
		User user = JSON.parseObject(data, User.class);
		UserService userService = new UserServiceImpl();
		try {
			user = userService.queryByNameAndPwd(user.getUsername(),user.getPassword());
			session.setAttribute(SESSION_NAME, user);
			session.setAttribute(SESSION__USER_COUNT, new UserCountListener());
			resp.getWriter().println(JSON.toJSON(user));
		} catch (Exception e) {
			logger.error("[LoginController:doPost]" + e.getMessage());
			resp.getWriter().print(1);
		}
	}
	
	

}
