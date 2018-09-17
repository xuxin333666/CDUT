package cn.kgc.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import cn.kgc.bean.PageBean;
import cn.kgc.exception.ServiceException;
import cn.kgc.model.Group;
import cn.kgc.model.Professional;
import cn.kgc.service.impl.GroupServiceImpl;
import cn.kgc.service.impl.ProfessionalServiceImpl;
import cn.kgc.service.intf.GroupService;
import cn.kgc.service.intf.ProfessionalService;
import cn.kgc.utils.GroupUtils;
import cn.kgc.utils.StringUtils;


public class GroupController extends CoreController {
	private static final Logger logger = LoggerFactory.getLogger(GroupController.class); 
	GroupService groupService = new GroupServiceImpl();

	public GroupController() {
		super.setController(this);
	}
	public void dels(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String idArrStr = req.getParameter("data");
		
		List<String> idArr = JSONArray.parseArray(idArrStr,String.class);
		
		try {
			groupService.deletes(idArr);
			resp.getWriter().print("删除成功");

		} catch (ServiceException e) {
			logger.error("[GroupController:dels]" + e.getMessage());
			resp.getWriter().print(e.getMessage());
		}
		
	}
	
	public void disable(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idArrStr = req.getParameter("data");
		
		List<String> idArr = JSONArray.parseArray(idArrStr,String.class);
		
		String msg = "";
		try {
			groupService.disable(idArr);
			msg = "停用成功";
		} catch (ServiceException e) {
			logger.error("[GroupController:disable]" + e.getMessage());
			msg = e.getMessage();
		}
		resp.getWriter().print(msg);
	}
	
	public void enable(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idArrStr = req.getParameter("data");
		
		List<String> idArr = JSONArray.parseArray(idArrStr,String.class);
		
		int status = 0;
		try {
			status = groupService.enable(idArr);
		} catch (ServiceException e) {
			logger.error("[GroupController:enable]" + e.getMessage());
		}
		
		resp.getWriter().print(status);
	}
	
	
	public void mainTable(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProfessionalService professionalService = new ProfessionalServiceImpl();
		Map<String, String[]> feilds = req.getParameterMap();
		PageBean<Group> pageBean = null;
		List<Professional> pros = null;
		try {
			pageBean = new PageBean<>(groupService.getCount(feilds));
			if(StringUtils.isNotEmpty(req.getParameter("page"))) {
				pageBean.setCurrentPage(Integer.parseInt(req.getParameter("page")));
			} else {
				pageBean.setCurrentPage(1);
			}
			
			pageBean = groupService.query(pageBean,feilds);
			
			pros = professionalService.query();
			
		} catch (ServiceException e) {
			logger.error("[GroupController:mainTable]" + e.getMessage());
		}
		req.setAttribute("pageBean", pageBean);
		req.setAttribute("pros", pros);
		req.setAttribute("maps", feilds);
		req.setAttribute("columnNames", GroupUtils.colName);
		req.setAttribute("fields", GroupUtils.fields);
		
		req.getRequestDispatcher("group.jsp").forward(req, resp);
	}
	
	public void pro_add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pid = req.getParameter("pid");
		String id = groupService.getNewId();
		
		Group group = new Group(id);
		group.setProfessional(new Professional(pid));
		
		req.setAttribute("group", group);
		req.setAttribute("selectMap", GroupUtils.selectMap);
		req.setAttribute("command", "add");
		req.getRequestDispatcher("pro_modify.jsp").forward(req, resp);
	}
	
	public void pro_modify(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		Group group;
		try {
			group = groupService.query(id);
			req.setAttribute("group", group);
			req.setAttribute("selectMap", GroupUtils.selectMap);
			req.setAttribute("command", "modify");
			req.getRequestDispatcher("pro_modify.jsp").forward(req, resp);
		} catch (ServiceException e) {
			logger.error("[GroupController:pro_modify]" + e.getMessage());
			req.setAttribute("msg", e.getMessage());
			req.getRequestDispatcher("/error.jsp").forward(req, resp);
		}

	}
	
	public void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
			logger.error("[GroupController:save]" + e.getMessage());
		}
		
		resp.getWriter().println(status);
	}
}
