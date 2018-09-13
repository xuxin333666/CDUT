package cn.kgc.controller.group;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.kgc.bean.PageBean;
import cn.kgc.exception.ServiceException;
import cn.kgc.model.Group;
import cn.kgc.service.impl.GroupServiceImpl;
import cn.kgc.service.intf.GroupService;
import cn.kgc.utils.GroupUtils;
import cn.kgc.utils.StringUtils;



@WebServlet("/admin/permissions/group/mainTable")
public class GroupController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(GroupController.class); 

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		GroupService groupService = new GroupServiceImpl();
		Map<String, String[]> feilds = req.getParameterMap();
		PageBean<Group> pageBean = null;
		try {
			pageBean = new PageBean<>(groupService.getCount(feilds));
			if(StringUtils.isNotEmpty(req.getParameter("page"))) {
				pageBean.setCurrentPage(Integer.parseInt(req.getParameter("page")));
			} else {
				pageBean.setCurrentPage(1);
			}
			
			pageBean = groupService.query(pageBean,feilds);
			
		} catch (ServiceException e) {
			logger.error("[GroupController:doPost]" + e.getMessage());
		}
		req.setAttribute("pageBean", pageBean);
		req.setAttribute("maps", feilds);
		req.setAttribute("columnNames", GroupUtils.colName);
		req.setAttribute("fields", GroupUtils.fields);
		
		req.getRequestDispatcher("group.jsp").forward(req, resp);
	}
	
	

}
