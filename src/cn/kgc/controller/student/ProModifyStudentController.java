package cn.kgc.controller.student;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.kgc.exception.ServiceException;
import cn.kgc.model.Student;
import cn.kgc.service.impl.StudentServiceImpl;
import cn.kgc.service.intf.StudentService;
import cn.kgc.utils.StudentUtils;



@WebServlet("/admin/permissions/student/pro_modify")
public class ProModifyStudentController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(ProModifyStudentController.class); 
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StudentService studnetService = new StudentServiceImpl();
		String id = req.getParameter("id");
		Student student;
		try {
			student = studnetService.query(id);
			
			req.setAttribute("student", student);
			req.setAttribute("selectMap", StudentUtils.selectMap);
			req.setAttribute("command", "modify");
			req.getRequestDispatcher("pro_modify.jsp").forward(req, resp);
		} catch (ServiceException e) {
			logger.error("[ProModifyStudentController:doPost]" + e.getMessage());
			req.setAttribute("msg", e.getMessage());
			req.getRequestDispatcher("/error.jsp").forward(req, resp);
		}

	}
	
	

}
