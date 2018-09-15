package cn.kgc.controller.student;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;

import cn.kgc.bean.PageBean;
import cn.kgc.dto.treeNode;
import cn.kgc.exception.ServiceException;
import cn.kgc.model.Student;
import cn.kgc.service.impl.StudentServiceImpl;
import cn.kgc.service.intf.StudentService;
import cn.kgc.utils.StringUtils;
import cn.kgc.utils.StudentUtils;



@WebServlet("/admin/permissions/student/mainTable")
public class StudentController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(StudentController.class); 

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StudentService studnetService = new StudentServiceImpl();
		Map<String, String[]> feilds = req.getParameterMap();
		String gid = req.getParameter("gid");
		PageBean<Student> pageBean = null;
		try {
			pageBean = new PageBean<>(studnetService.getCount(feilds));
			if(StringUtils.isNotEmpty(req.getParameter("page"))) {
				pageBean.setCurrentPage(Integer.parseInt(req.getParameter("page")));
			} else {
				pageBean.setCurrentPage(1);
			}
			
			pageBean = studnetService.query(pageBean,feilds);
			
			
		} catch (ServiceException e) {
			logger.error("[StudentController:doPost:ѧ����ѯʧ��]" + e.getMessage());
		}
		
		List<treeNode> proNodes = null;
		try {
			proNodes = studnetService.createTreeNode(gid);
		} catch (ServiceException e) {
			logger.error("[StudentController:doPost:������ʧ��]" + e.getMessage());
		}
		
		req.setAttribute("pageBean", pageBean);
		req.setAttribute("maps", feilds);
		req.setAttribute("selectMap", StudentUtils.selectMap);
		req.setAttribute("columnNames", StudentUtils.colName);
		req.setAttribute("fields", StudentUtils.fields);
		
		req.setAttribute("nodes", JSONArray.toJSON(proNodes));
		
		req.getRequestDispatcher("student.jsp").forward(req, resp);
	}
	
	

}
