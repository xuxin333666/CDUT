package cn.kgc.controller.group;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.kgc.exception.ServiceException;
import cn.kgc.model.Group;
import cn.kgc.service.impl.GroupServiceImpl;
import cn.kgc.service.intf.GroupService;
import cn.kgc.utils.GroupUtils;



@WebServlet("/admin/permissions/group/pro_modify")
public class ProModifyGroupController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(ProModifyGroupController.class); 
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		GroupService groupService = new GroupServiceImpl();
		String id = req.getParameter("id");
		Group group;
		try {
			group = groupService.query(id);
			req.setAttribute("group", group);
			req.setAttribute("selectMap", GroupUtils.selectMap);
			req.setAttribute("command", "modify");
			req.getRequestDispatcher("pro_modify.jsp").forward(req, resp);
		} catch (ServiceException e) {
			logger.error("[ProModifyGroupController:doPost]" + e.getMessage());
			req.setAttribute("msg", e.getMessage());
			req.getRequestDispatcher("/error.jsp").forward(req, resp);
		}

	}
	
	

}
