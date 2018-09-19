package cn.kgc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;

import cn.kgc.dto.treeNode;
import cn.kgc.exception.ServiceException;
import cn.kgc.model.Perms;
import cn.kgc.service.impl.PermsServiceImpl;
import cn.kgc.service.intf.PermsService;

public class PermsController extends CoreController {

	private static List<Perms> menus = new ArrayList<>();
	private PermsService permsService = new PermsServiceImpl();
	private static final Logger logger = LoggerFactory.getLogger(PermsController.class); 

	
	public PermsController() {
		super.setController(this);
	}

	private void createMenus(List<Perms> secMenus,String id) {
		for (Perms menu : menus) {
			if(menu.getParentMenu() != null && id.equals(menu.getParentMenu().getId())) {
				secMenus.add(menu);
				createMenus(secMenus,menu.getId());
			}
		}
	}

	public void main(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String command = req.getParameter("command");
		
		try {
			menus = permsService.getMenu();
			List<Perms> mainMenus = new ArrayList<>();
			List<Perms> secMenus = new ArrayList<>();
			for (Perms menu : menus) {
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
		} catch (ServiceException e) {
			logger.error("[PermsController:main]" + e.getMessage());
			req.setAttribute("msg", e.getMessage());
			req.getRequestDispatcher("/error.jsp").forward(req, resp);
		}
		
		
	}
	
	public void mainTable(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		List<treeNode> Nodes = null;
		try {
			Nodes = permsService.getTreeNodes(null);
			
		} catch (ServiceException e) {
			logger.error("[PermsController:mainTable]" + e.getMessage());
		}
		req.setAttribute("nodes", JSONArray.toJSON(Nodes));

		
		req.getRequestDispatcher("perms.jsp").forward(req, resp);
		
	}
	
	public void getSubTree(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String pid = req.getParameter("pid");
		List<treeNode> Nodes = null;
		try {
			Nodes = permsService.getTreeNodes(pid);
			
		} catch (ServiceException e) {
			logger.error("[PermsController:mainTable]" + e.getMessage());
		}
		resp.getWriter().print(JSONArray.toJSON(Nodes));
		
	}

	public void pro_modify(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String id = req.getParameter("id");
		Perms perms;
		try {
			perms = permsService.query(id);
			req.setAttribute("perms", perms);
			req.setAttribute("command", "modify");
			req.getRequestDispatcher("pro_modify.jsp").forward(req, resp);
		} catch (ServiceException e) {
			logger.error("[PermsController:pro_modify]" + e.getMessage());
			req.setAttribute("msg", e.getMessage());
			req.getRequestDispatcher("/error.jsp").forward(req, resp);
		}
	}

}
