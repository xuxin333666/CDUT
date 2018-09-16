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



@WebServlet("/admin/permissions/student/student_regist")
public class StudentRegistController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(StudentRegistController.class); 

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
			logger.error("[StudentRegistController:doPost:学生查询失败]" + e.getMessage());
		}
		
		List<treeNode> proNodes = null;
		try {
			proNodes = studnetService.createTreeNode(gid);
		} catch (ServiceException e) {
			logger.error("[StudentReportController:doPost:树构建失败]" + e.getMessage());
		}
		
		req.setAttribute("pageBean", pageBean);
		req.setAttribute("maps", feilds);
		req.setAttribute("selectMap", StudentUtils.selectMap);
		req.setAttribute("columnNames", StudentUtils.colName_regist);
		req.setAttribute("fields", StudentUtils.fields_regist);
		
		req.setAttribute("nodes", JSONArray.toJSON(proNodes));
		
		req.getRequestDispatcher("student_regist.jsp").forward(req, resp);
	}
	
	

}
