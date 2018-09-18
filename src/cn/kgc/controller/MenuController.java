package cn.kgc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.kgc.model.Menu;

public class MenuController extends CoreController {

	private static List<Menu> menus = new ArrayList<>();
	
	static {
		menus.add(new Menu("100", "��������", null, null, null, "main?command=baseDate", "baseDate:query", null));
		menus.add(new Menu("110", "��������", "#icon-users", null, null, null, "base:main", new Menu("100")));
		menus.add(new Menu("111", "רҵ����", null, null, null, "permissions/professional/mainTable", "professional:main", new Menu("110")));
		menus.add(new Menu("112", "�༶����", null, null, null, "permissions/group/mainTable", "group:main", new Menu("110")));
		menus.add(new Menu("113", "ѧ������", null, null, null, "permissions/student/mainTable", "student:main", new Menu("110")));
		
		menus.add(new Menu("200", "ѧ���ճ�", null, null, null, "main?command=studentDay", "studentDay:query", null));
		
		menus.add(new Menu("300", "ѧ������", null, null, null, "main?command=studentStatus", "studentStatus:query", null));
		menus.add(new Menu("310", "ѧ��ҵ��", "#icon-users", null, null, null, "service_student:main", new Menu("300")));
		menus.add(new Menu("311", "ѧ������", null, null, null, "permissions/student/student_report", "student_report:main", new Menu("310")));
		menus.add(new Menu("312", "ѧ��ע��", null, null, null, "permissions/student/student_regist", "student_regist:main", new Menu("310")));
		menus.add(new Menu("320", "ͳ�Ʋ�ѯ", "#icon-users", null, null, null, "statisticalQuery:main", new Menu("300")));
		menus.add(new Menu("321", "רҵ������ѯ", null, null, null, "permissions/professional/statisticalQuery.jsp", "professional_num_statistical:main", new Menu("320")));
		
		menus.add(new Menu("400", "ϵͳ����", null, null, null, "main?command=systemManagement", "systemManagement:query", null));
		
	}
	
	public MenuController() {
		super.setController(this);
	}

	private void createMenus(List<Menu> secMenus,String id) {
		for (Menu menu : menus) {
			if(menu.getParentMenu() != null && id.equals(menu.getParentMenu().getId())) {
				secMenus.add(menu);
				createMenus(secMenus,menu.getId());
			}
		}
	}

	public void main(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
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


}
