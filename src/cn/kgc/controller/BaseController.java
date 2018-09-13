package cn.kgc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.kgc.model.Menu;

@WebServlet("/admin/main")
public class BaseController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static List<Menu> menus = new ArrayList<>();
	
	static {
		menus.add(new Menu("100", "基础数据", null, null, null, "main?command=baseDate", "baseDate", null));
		menus.add(new Menu("110", "基础设置", "#icon-users", null, null, null, "base", new Menu("100")));
		menus.add(new Menu("111", "专业管理", null, null, null, "permissions/professional/mainTable", "professional", new Menu("110")));
		menus.add(new Menu("112", "班级管理", null, null, null, "permissions/group/mainTable", "group", new Menu("110")));
		menus.add(new Menu("113", "学生档案", null, null, null, "", "student", new Menu("110")));
		
		menus.add(new Menu("200", "学生日常", null, null, null, "main?command=studentDay", "studentDay", null));
		menus.add(new Menu("300", "学籍管理", null, null, null, "main?command=studentStatus", "studentStatus", null));
		menus.add(new Menu("400", "系统管理", null, null, null, "main?command=systemManagement", "systemManagement", null));
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String command = req.getParameter("command");
		
		List<Menu> mainMenus = new ArrayList<>();
		List<Menu> secMenus = new ArrayList<>();
		for (Menu menu : menus) {
			if(Integer.parseInt(menu.getId()) % 100 == 0) {
				mainMenus.add(menu);
				if(command != null && command.equals(menu.getTagName())) {
					createMenus(secMenus,menu.getId());
				}
			}
		}
		
		req.setAttribute("mainMenus", mainMenus);
		req.setAttribute("secMenus", secMenus);
		req.getRequestDispatcher("main.jsp").forward(req, resp);
	}
	
	private void createMenus(List<Menu> secMenus,String id) {
		for (Menu menu : menus) {
			if(menu.getParentMenu() != null && id.equals(menu.getParentMenu().getId())) {
				secMenus.add(menu);
				createMenus(secMenus,menu.getId());
			}
		}
	}


}
