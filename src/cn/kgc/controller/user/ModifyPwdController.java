package cn.kgc.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import cn.kgc.service.impl.UserServiceImpl;
import cn.kgc.service.intf.UserService;

@WebServlet("/admin/modifyUserPwd")
public class ModifyPwdController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(ModifyPwdController.class); 

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		UserService userService = new UserServiceImpl();
		try {
			userService.modifyPwd(username,password);
			req.getRequestDispatcher("main").forward(req, resp);
		} catch (Exception e) {
			logger.error("[LoginController:doPost]" + e.getMessage());
			req.getRequestDispatcher("/error.jsp").forward(req, resp);
		}
	}
	
	

}
