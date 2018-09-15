package cn.kgc.controller.student;

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
import cn.kgc.model.Student;
import cn.kgc.service.impl.StudentServiceImpl;
import cn.kgc.service.intf.StudentService;



@WebServlet("/admin/permissions/student/save")
public class SaveStudentController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(SaveStudentController.class); 
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StudentService studnetService = new StudentServiceImpl();
		String command = req.getParameter("command");
		String gid = req.getParameter("gid");
		String data = req.getParameter("data");
		
		Student student = JSON.parseObject(data, Student.class);
		
		student.setGroup(new Group(gid));
		
		int status = 0;
		try {
			if("add".equals(command)) {
				status = studnetService.add(student);
			} else if("modify".equals(command)) {
				status = studnetService.modify(student);
			}
			resp.getWriter().println(status);
		} catch (ServiceException e) {
			logger.error("[SaveStudentController:doPost]" + e.getMessage());
			resp.getWriter().println(e.getMessage());
		}
		
	}
	
	

}
