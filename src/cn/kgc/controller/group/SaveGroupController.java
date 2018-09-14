package cn.kgc.controller.group;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

import cn.kgc.exception.ServiceException;
import cn.kgc.model.Group;
import cn.kgc.model.Professional;
import cn.kgc.service.impl.GroupServiceImpl;
import cn.kgc.service.intf.GroupService;



@WebServlet("/admin/permissions/group/save")
public class SaveGroupController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(SaveGroupController.class); 
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		GroupService groupService = new GroupServiceImpl();
		String command = req.getParameter("command");
		String pid = req.getParameter("pid");
		String data = req.getParameter("data");
		
		Group group = JSON.parseObject(data, Group.class);
		group.setProfessional(new Professional(pid));
		int status = 0;
		try {
			if("add".equals(command)) {
				status = groupService.add(group);
			} else if("modify".equals(command)) {
				status = groupService.modify(group);
			}
		} catch (ServiceException e) {
			logger.error("[SaveGroupController:doPost]" + e.getMessage());
		}
		
		resp.getWriter().println(status);
	}
	
	

}
