package cn.kgc.controller.group;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.kgc.model.Group;
import cn.kgc.service.impl.GroupServiceImpl;
import cn.kgc.service.intf.GroupService;
import cn.kgc.utils.GroupUtils;



@WebServlet("/admin/permissions/group/pro_add")
public class ProAddGroupController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		GroupService groupService = new GroupServiceImpl();
		String id = groupService.getNewId();

		req.setAttribute("group", new Group(id));
		req.setAttribute("selectMap", GroupUtils.selectMap);
		req.setAttribute("command", "add");
		req.getRequestDispatcher("pro_modify.jsp").forward(req, resp);
	}
	
	

}
